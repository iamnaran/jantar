package com.iamnaran.jantar.camera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CameraPreviewScreen(onImageCaptured: (String) -> Unit) {

    val viewModel: CameraPreviewViewModel = viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    var permissionStatus by remember { mutableStateOf(PermissionStatus.UNKNOWN) }
    var isPermissionDenied by remember { mutableStateOf(false) }
    var showRationale by remember { mutableStateOf(false) }

    val hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            permissionStatus = if (isGranted) {
                PermissionStatus.GRANTED
            } else {
                PermissionStatus.DENIED
            }
        }

    val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
        context as Activity,
        Manifest.permission.CAMERA
    )

    LaunchedEffect(hasPermission) {
        if (!hasPermission && permissionStatus == PermissionStatus.UNKNOWN) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        } else if (hasPermission) {
            permissionStatus = PermissionStatus.GRANTED
        }
    }

    when (permissionStatus) {
        PermissionStatus.UNKNOWN -> {
            CameraPermissionContent(){
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
        PermissionStatus.DENIED -> {
            if (shouldShowRationale) {
                CameraPermissionRationaleContent {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            } else {
                CameraPermissionContent {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }
        PermissionStatus.GRANTED -> {
            CameraPreviewContent(viewModel, lifecycleOwner)
        }
    }
}

@Composable
fun CameraPreviewContent(
    viewModel: CameraPreviewViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    surfaceRequest?.let { request ->
        CameraXViewfinder(
            surfaceRequest = request,
            modifier = Modifier.fillMaxSize()
        )
    }

}


@Composable
fun CameraPermissionContent(requestPermission: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            requestPermission()
        }) {
            Text("Request Permission")
        }
    }

}

@Composable
fun CameraPermissionRationaleContent(
    onPermissionRationale: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Camera permission is required to take pictures. Please grant access.")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onPermissionRationale() }) {
            Text("Grant Permission")
        }
    }
}


