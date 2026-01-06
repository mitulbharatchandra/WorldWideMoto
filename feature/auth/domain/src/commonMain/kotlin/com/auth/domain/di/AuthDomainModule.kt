package com.auth.domain.di

import com.auth.domain.usecases.GetCurrentUser
import com.auth.domain.usecases.LoginWithApple
import com.auth.domain.usecases.LoginWithEmail
import com.auth.domain.usecases.LoginWithGoogle
import com.auth.domain.usecases.LoginWithPhone
import com.auth.domain.usecases.Logout
import com.auth.domain.usecases.Signup
import org.koin.dsl.module

val authDomainModule = module {

    factory {
        LoginWithEmail(
            repository = get()
        )
    }

    factory {
        Signup(
            repository = get()
        )
    }

    factory {
        LoginWithPhone(
            repository = get()
        )
    }

    factory {
        LoginWithGoogle(
            repository = get()
        )
    }

    factory {
        LoginWithApple(
            repository = get()
        )
    }

    factory {
        GetCurrentUser(
            repository = get()
        )
    }

    factory {
        Logout(
            repository = get()
        )
    }
}
