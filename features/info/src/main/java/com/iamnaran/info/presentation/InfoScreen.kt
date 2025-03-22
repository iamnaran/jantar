package com.iamnaran.info.presentation

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.iamnaran.info.data.Info
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoScreen(capturedImageUri: String, onBottomSheetDismiss: () -> Unit) {
    val viewModel: InfoViewModel = koinViewModel()
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(capturedImageUri) {
        viewModel.processAndFetchInfo(capturedImageUri)
    }

    InfoScreenContent(capturedImageUri, uiState.value) {
        onBottomSheetDismiss()
    }

}

@Composable
fun InfoScreenContent(
    capturedImageUri: String,
    uiState: InfoUIState<Info>,
    onBottomSheetDismiss: () -> Unit
) {

    val uri = Uri.parse(capturedImageUri)

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build()
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
    ) {
        Text("Image Captured")
        Image(
            painter = painter,
            contentDescription = "Captured Image Thumbnail",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .aspectRatio(1f)
                .height(200.dp),
            contentScale = ContentScale.Crop,
        )

        if (uiState is InfoUIState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }

        if (uiState is InfoUIState.Success) {
            InfoBottomSheet(uiState.data) {
                onBottomSheetDismiss()
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoBottomSheet(infoData: Info, onBottomSheetDismiss: () -> Unit) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    ModalBottomSheet(
        onDismissRequest = {
            onBottomSheetDismiss()
        },
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(0.85f)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Medicine Details",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Medicine Name
            InfoCard(title = "Name", description = infoData.name)

            // Main & Secondary Usages
            InfoCard(title = "Main Usages", description = infoData.mainUsages)
            InfoCard(title = "Secondary Usages", description = infoData.secondaryUsages)

            // Type & Medicine Status
            InfoCard(title = "Type", description = infoData.type)
            InfoCard(title = "Is Medicine?", description = if (infoData.isMedicine) "Yes" else "No")

            // Dosage Information
            InfoCard(title = "Adult Dosage", description = infoData.dosages.adult)
            InfoCard(title = "Children Dosage", description = infoData.dosages.children)

            // Storage & Warnings
            InfoCard(title = "Storage Instructions", description = infoData.storageInstructions)
            InfoCard(title = "Warning", description = infoData.warning)

            // Other Details (Batch, Expiry, Price)
            InfoCard(title = "Batch Number", description = infoData.otherDetails.batchNumber)
            InfoCard(title = "Expiry Date", description = infoData.otherDetails.expiryDate)
            InfoCard(title = "Price", description = "Rs. ${infoData.otherDetails.price}")

            Spacer(modifier = Modifier.height(16.dp))
        }

    }


}


@Composable
fun InfoCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

