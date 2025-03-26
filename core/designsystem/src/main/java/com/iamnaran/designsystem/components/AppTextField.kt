package com.iamnaran.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.designsystem.theme.JantarTheme
import com.iamnaran.designsystem.theme.dimens

@Composable
fun EmailInput(
    label: String,
    placeholder: String,
    icon: ImageVector,
    currentValue: String,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.normal)
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        TextField(
            value = currentValue,
            singleLine = true,
            placeholder = { Text(placeholder) },
            keyboardActions = keyboardActions,
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                errorIndicatorColor = MaterialTheme.colorScheme.onError,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedPlaceholderColor = Color.Transparent,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(start = MaterialTheme.dimens.zeroDim)
                .focusRequester(focusRequester = focusRequester!!),
            onValueChange = { onValueChange(it) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                autoCorrectEnabled = true
            ),

        )

    }

}


@Composable
fun PasswordInput(
    label: String,
    placeholder: String,
    icon: ImageVector,
    currentValue: String,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.normal)
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        TextField(
            value = currentValue,
            singleLine = true,
            placeholder = { Text(placeholder) },
            keyboardActions = keyboardActions,
            shape = MaterialTheme.shapes.medium,
            trailingIcon = {
                val passwordIcon = if (passwordVisible) {
                    AppIcons.PasswordVisibility
                } else {
                    AppIcons.PasswordVisibilityOff
                }
                val description = if (passwordVisible) {
                    "Hide Password"
                } else {
                    "Show Password"
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = passwordIcon, contentDescription = description)
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                errorIndicatorColor = MaterialTheme.colorScheme.onError,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedPlaceholderColor = Color.Transparent,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),

            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .focusRequester(focusRequester = focusRequester!!),
            onValueChange = { onValueChange(it) },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                autoCorrectEnabled = true
            ),
        )

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JantarTheme {
        EmailInput(
            "Phone/Email Address",
            "nrn.panthi@gmail.com",
            AppIcons.Email,
            "nrn.panthi@gmail.com",
            focusRequester = FocusRequester(),
            keyboardActions = KeyboardActions.Default,
        ) {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PasswordPreview() {
    JantarTheme {
        PasswordInput(
            "Password",
            "Password",
            AppIcons.Email,
            "nrn.panthi@gmail.com",
            focusRequester = FocusRequester(),
            keyboardActions = KeyboardActions.Default,
        ) {

        }
    }
}