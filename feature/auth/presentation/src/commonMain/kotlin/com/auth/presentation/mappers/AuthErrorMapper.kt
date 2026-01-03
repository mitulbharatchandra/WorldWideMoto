package com.auth.presentation.mappers

import com.kmp.auth.api.model.AuthError
import org.jetbrains.compose.resources.StringResource
import worldwidemoto.feature.auth.presentation.generated.resources.Res
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_email_already_in_use
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_invalid_action_code
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_invalid_credentials
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_invalid_email
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_multi_factor_required
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_network
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_recent_login_required
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_too_many_requests
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_unknown
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_user_not_found
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_weak_password
import worldwidemoto.feature.auth.presentation.generated.resources.auth_error_wrong_password

fun AuthError.toMessageRes(): StringResource =
    when (this) {
        AuthError.InvalidEmail ->
            Res.string.auth_error_invalid_email

        AuthError.WrongPassword ->
            Res.string.auth_error_wrong_password

        AuthError.UserNotFound ->
            Res.string.auth_error_user_not_found

        AuthError.EmailAlreadyInUse ->
            Res.string.auth_error_email_already_in_use

        AuthError.WeakPassword ->
            Res.string.auth_error_weak_password

        AuthError.NetworkError ->
            Res.string.auth_error_network

        AuthError.TooManyRequests ->
            Res.string.auth_error_too_many_requests

        AuthError.InvalidActionCode ->
            Res.string.auth_error_invalid_action_code

        AuthError.RecentLoginRequired ->
            Res.string.auth_error_recent_login_required

        AuthError.InvalidCredentials ->
            Res.string.auth_error_invalid_credentials

        is AuthError.AuthMultiFactor ->
            Res.string.auth_error_multi_factor_required

        is AuthError.Unknown ->
            Res.string.auth_error_unknown
    }
