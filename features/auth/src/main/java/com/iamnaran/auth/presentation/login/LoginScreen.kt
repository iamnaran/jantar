package com.iamnaran.auth.presentation.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iamnaran.designsystem.R.drawable
import com.iamnaran.designsystem.components.AppButton
import com.iamnaran.designsystem.components.AppProgressButton
import com.iamnaran.designsystem.components.EmailInput
import com.iamnaran.designsystem.components.PasswordInput
import com.iamnaran.designsystem.components.SocialLogin
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.designsystem.theme.JantarTheme
import com.iamnaran.designsystem.theme.dimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    val viewModel: LoginViewModel = koinViewModel()

    val context = LocalContext.current
    val uiState = viewModel.loginState.collectAsStateWithLifecycle()
    val inputFieldsState = viewModel.loginInputFieldsState.collectAsStateWithLifecycle()

    if (uiState.value is LoginState.Success) {
        LaunchedEffect(Unit) {
            navigateToMain()
        }
    }

    LoginContent(
        inputFieldsState.value.emailOrPhone,
        inputFieldsState.value.password,
        onEmailChange = {
            viewModel.onEvent(event = LoginUiEvent.OnEmailOrPhoneChanged(it))
        },
        onPasswordChange = {
            viewModel.onEvent(event = LoginUiEvent.OnPasswordChanged(it))
        },
        isLoading = uiState.value is LoginState.Loading,
        onLoginClick = {
            viewModel.onEvent(event = LoginUiEvent.OnLogin)
        })
}

@Composable
fun LoginContent(
    emailValue: String,
    passwordValue: String,
    onEmailChange: (email: String) -> Unit,
    onPasswordChange: (email: String) -> Unit,
    isLoading: Boolean,
    onLoginClick: () -> Unit,
) {

    val passwordFocusRequester = FocusRequester()
    val focusManager: FocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.dimens.default)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f, fill = false)
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = drawable.jantar_app_logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f, fill = false)
                .padding(MaterialTheme.dimens.large),
        ) {

            EmailInput(
                "Phone or Email Address",
                "yourmail@domain.com",
                AppIcons.Email,
                emailValue,
                FocusRequester(),
                KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
            ) {
                onEmailChange(it)
            }

            PasswordInput(
                "Password",
                "•••••",
                AppIcons.Password,
                passwordValue,
                passwordFocusRequester,
                KeyboardActions(onDone = { focusManager.clearFocus() })
            ) {

                onPasswordChange(it)
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.sizeNormal))

            Text(
                "Forgot Password?",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.normal),
                textAlign = TextAlign.End,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.sizeLarge))

            AppProgressButton("LOGIN", isLoading) {
                onLoginClick()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                "or sign-in with",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.normal),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.sizeLarge))

            SocialLogin(onGoogleClick = {

            }, onFacebookClick = {

            })

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.sizeLarge))

            Text(
                "Do not have an account? Register",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.normal),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.sizeLarge))

        }
    }


}

@Preview("Login Screen")
@Composable
fun LoginScreenPreview() {
    JantarTheme {
        LoginContent("email", "pass", {}, {}, true) { }
    }

}