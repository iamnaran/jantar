package com.iamnaran.camera.camera.presentation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iamnaran.camera.camera.data.PermissionStatus
import com.iamnaran.camera.camera.data.PreviewMedia
import com.iamnaran.designsystem.AppIcons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CameraPreviewScreen(onImageCaptured: (PreviewMedia) -> Unit) {

    val viewModel: CameraPreviewViewModel = viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var permissionStatus by remember { mutableStateOf(PermissionStatus.UNKNOWN) }

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
            CameraPermissionContent {
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
            CameraPreviewContent(
                viewModel,
                onImageCaptured,
                coroutineScope,
                context,
                lifecycleOwner
            )
        }
    }
}

@Composable
fun CameraPreviewContent(
    viewModel: CameraPreviewViewModel,
    onImageCaptured: (PreviewMedia) -> Unit,
    coroutineScope: CoroutineScope,
    context: Activity,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
    var isFlashOn by remember { mutableStateOf(false) }

    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        surfaceRequest?.let { request ->
            CameraXViewfinder(
                surfaceRequest = request,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {

                    SwitchCameraToggleButton {
                        coroutineScope.launch {
                            viewModel.toggleCamera(context, lifecycleOwner)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    ShutterButton {
                        viewModel.captureImage(context = context) { data ->
                            onImageCaptured(data)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    FlashToggleButton(isFlashOn) {
                        isFlashOn = it
                    }
                }
            }
        }
    }
}


@Composable
fun ShutterButton(onClick: () -> Unit) {

    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 500f),
        label = "scale"
    )
    val buttonColor by remember { mutableStateOf(Color.White) }
    val pressedColor = Color.LightGray

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .border(width = 2.dp, color = Color.White, shape = CircleShape)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(70.dp)
                .scale(scale)
                .background(if (isPressed) pressedColor else buttonColor, CircleShape)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            tryAwaitRelease() // Waits until release
                            isPressed = false
                            onClick()
                        }
                    )
                }
        ) {
            Icon(
                imageVector = AppIcons.Camera,
                contentDescription = "Camera",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun FlashToggleButton(
    isFlashOn: Boolean, onToggle: (Boolean) -> Unit
) {

    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 500f),
        label = "scale"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
            .scale(scale)
            .background(if (isFlashOn) Color.Yellow else Color.Gray, CircleShape)
            .clickable {
                isPressed = true
                onToggle(!isFlashOn)
                isPressed = false
            }
    ) {
        Icon(
            imageVector = if (isFlashOn) AppIcons.FlashOn else AppIcons.FlashOff,
            contentDescription = "Flash Toggle",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp)
        )
    }
}


@Composable
fun SwitchCameraToggleButton(
    onSwitchCamera: () -> Unit
) {

    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 500f),
        label = "scale"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
            .scale(scale)
            .background(Color.Gray, CircleShape)
            .clickable {
                isPressed = true
                onSwitchCamera()
                isPressed = false
            }
    ) {
        Icon(
            imageVector = AppIcons.CameraSwitch,
            contentDescription = "Camera Switch",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp)
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


