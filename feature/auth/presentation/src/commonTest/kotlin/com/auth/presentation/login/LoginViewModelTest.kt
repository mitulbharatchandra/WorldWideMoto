package com.auth.presentation.login

import com.auth.domain.model.EmailValidationError
import com.auth.domain.model.PasswordValidationError
import com.auth.domain.model.PasswordValidationState
import com.auth.domain.usecases.GetCurrentUser
import com.auth.domain.usecases.LoginWithEmail
import com.auth.domain.usecases.Signup
import com.kmp.auth.api.model.AuthError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.Boolean
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var fakeRepository: FakeAuthRepositoryForPresentation
    private lateinit var loginWithEmail: LoginWithEmail
    private lateinit var signup: Signup
    private lateinit var getCurrentUser: GetCurrentUser
    private lateinit var viewModel: LoginViewModel

    private val testDispatcher = StandardTestDispatcher()


    @BeforeTest
    fun setup() {
        fakeRepository = FakeAuthRepositoryForPresentation()
        loginWithEmail = LoginWithEmail(fakeRepository)
        signup = Signup(fakeRepository)
        getCurrentUser = GetCurrentUser(fakeRepository)

        viewModel = LoginViewModel(
            loginWithEmail = loginWithEmail,
            signUp = signup,
            getCurrentUser = getCurrentUser
        )
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_sets_loading_and_clears_on_success() = runTest {
        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "user@example.com",
                password = "StrongPass1"
            )
        )

        assertTrue(viewModel.state.value.isLoading)

        advanceUntilIdle()

        assertFalse(viewModel.state.value.isLoading)
        assertTrue(fakeRepository.loginInvoked)
    }

    @Test
    fun signup_sets_loading_and_clears_on_success() = runTest {
        viewModel.onEvent(
            LoginUIEvent.signupWithEmail(
                email = "user@example.com",
                password = "StrongPass1"
            )
        )

        assertTrue(viewModel.state.value.isLoading)

        advanceUntilIdle()

        assertFalse(viewModel.state.value.isLoading)
        assertTrue(fakeRepository.signupInvoked)
    }

    @Test
    fun email_validation_error_is_exposed_in_state() = runTest {
        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "bad",
                password = "StrongPass1"
            )
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertNotNull(state.emailValidationError)
        assertFalse(state.isLoading)
    }

    @Test
    fun password_validation_error_is_exposed_in_state() = runTest {
        val authError = PasswordValidationError.InvalidPassword(passwordValidationState = null)
        fakeRepository.shouldThrow = authError
        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "user@example.com",
                password = "weak"
            )
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertNotNull(state.passwordValidationError)
        assertFalse(state.isLoading)
    }

    @Test
    fun auth_error_is_exposed_in_state() = runTest {
        val authError = AuthError.WrongPassword
        fakeRepository.shouldThrow = authError
        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "user@example.com",
                password = "StrongPass1"
            )
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertNotNull(state.authError)
        assertFalse(state.isLoading)
    }

    @Test
    fun generic_throwable_is_exposed_in_state() = runTest {
        val exception = RuntimeException("Boom")
        fakeRepository.shouldThrow = exception
        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "user@example.com",
                password = "StrongPass1"
            )
        )

        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(exception, state.throwable)
        assertFalse(state.isLoading)
    }

    @Test
    fun second_action_is_ignored_while_loading() = runTest {
        // Suspend forever to simulate long-running call

        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "user@example.com",
                password = "StrongPass1"
            )
        )

        viewModel.onEvent(
            LoginUIEvent.LoginWithEmail(
                email = "user2@example.com",
                password = "StrongPass1"
            )
        )
        val state = viewModel.state.value
        assertTrue(state.isLoading)
        advanceUntilIdle()
        assertTrue(state.isLoading)
    }

}