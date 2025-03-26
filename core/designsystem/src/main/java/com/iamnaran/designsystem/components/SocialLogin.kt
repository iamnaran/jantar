package com.iamnaran.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.iamnaran.designsystem.R
import com.iamnaran.designsystem.theme.dimens

@Composable
fun SocialLogin(onGoogleClick: () -> Unit, onFacebookClick: () -> Unit) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_social_facebook),
            contentDescription = "Facebook",
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable {
                    onGoogleClick()
                }
        )

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.sizeLarge))

        Icon(
            painter = painterResource(R.drawable.ic_social_google),
            contentDescription = "Google",
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable {
                    onFacebookClick()
                }
        )

    }


}