package com.iamnaran.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.designsystem.theme.JantarTheme

@Composable
fun EmailInput(
    label: String,
    icon: ImageVector,
    currentValue: String,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit
) {

    Column(modifier = Modifier.padding()) {

        Text(text = "Phone/Email Address", modifier = Modifier.padding(8.dp))

        TextField(
            value = currentValue,
            singleLine = true,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .padding(8.dp)
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
    icon: ImageVector,
    currentValue: String,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit
) {

    Column(modifier = Modifier.padding()) {

        Text(text = "Phone/Email Address", modifier = Modifier.padding(8.dp))

        TextField(
            value = currentValue,
            singleLine = true,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .padding(8.dp)
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JantarTheme {
        EmailInput(
            "Email", AppIcons.Email,
            "nrn.panthi@gmail.com",
            focusRequester = FocusRequester(),
            keyboardActions = KeyboardActions.Default,
        ) {

        }
    }
}