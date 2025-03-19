package com.iamnaran.jantar.camera

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class CameraPreviewViewModel : ViewModel() {
    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    private lateinit var cameraProvider: ProcessCameraProvider
    private var camera: Camera? = null
    private var currentCameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var imageCapture: ImageCapture


    fun bindToCamera(context: Context, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider { request ->
                        _surfaceRequest.update { request }
                    }
                }

                imageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .build()

                camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    currentCameraSelector,
                    preview,
                    imageCapture
                )
            }, ContextCompat.getMainExecutor(context))
        }
    }

    fun toggleCamera(context: Context, lifecycleOwner: LifecycleOwner) {
        if (!::cameraProvider.isInitialized) return

        currentCameraSelector = if (currentCameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            CameraSelector.DEFAULT_FRONT_CAMERA
        } else {
            CameraSelector.DEFAULT_BACK_CAMERA
        }

        cameraProvider.unbindAll()
        bindToCamera(context, lifecycleOwner)
    }

    fun captureImage(context: Context, onImageCaptured: (PreviewMedia) -> Unit) {

        if (!::imageCapture.isInitialized) {
            onImageCaptured(PreviewMedia("", PreviewMediaStatus.UNKNOWN))
            return
        }

        val outputDirectory = getOutputDirectory(context)
        val photoFile = File(
            outputDirectory,
            "IMG_${
                SimpleDateFormat(
                    "yyyyMMdd_HHmmss",
                    Locale.US
                ).format(System.currentTimeMillis())
            }.jpg"
        )

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputFileOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    onImageCaptured(
                        PreviewMedia(
                            Uri.fromFile(photoFile).toString(),
                            PreviewMediaStatus.UNKNOWN
                        )
                    )
                }

                override fun onError(exception: ImageCaptureException) {
                    onImageCaptured(PreviewMedia("", PreviewMediaStatus.ERROR))

                }
            }
        )

    }

    private fun getOutputDirectory(context: Context): File {
        val mediaDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return mediaDir ?: context.filesDir
    }

    private fun unbindCamera() {
        // Unbind all use cases when stopping the camera
        cameraProvider.unbindAll()
        _surfaceRequest.value = null
    }

    override fun onCleared() {
        unbindCamera()
        super.onCleared()
    }

}