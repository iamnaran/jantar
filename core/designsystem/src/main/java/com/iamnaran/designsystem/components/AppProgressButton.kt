package com.iamnaran.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.iamnaran.designsystem.theme.JantarTheme
import com.iamnaran.designsystem.theme.dimens

@Composable
fun AppProgressButton(
    label: String,
    isLoading: Boolean,
    onClickAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraSmall)
            .height(MaterialTheme.dimens.sizeButton)
            .clickable {
                onClickAction()
            }
            .padding(MaterialTheme.dimens.paddingDefault)
            .background(
                MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.extraSmall
            ),
        contentAlignment = Alignment.Center

    ) {

        if (isLoading) {
            AppCircularProgressIndicator()
        } else {

            Text(
                text = label,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.dimens.paddingNormal,
                        horizontal = MaterialTheme.dimens.paddingNormal
                    )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ButtonProgressPreview() {
    JantarTheme {
        AppProgressButton("Login", isLoading = false) {

        }
    }
}