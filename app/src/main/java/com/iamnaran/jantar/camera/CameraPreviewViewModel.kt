package com.iamnaran.jantar.camera

import android.content.Context
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CameraPreviewViewModel : ViewModel() {
    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    private lateinit var cameraProvider: ProcessCameraProvider
    private var camera: Camera? = null

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequest.update { newSurfaceRequest }
        }
    }

    fun bindToCamera(context: Context, lifecycleOwner: LifecycleOwner) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
            preview.setSurfaceProvider { request ->
                _surfaceRequest.value = request
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            camera = cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview
            )
        }, ContextCompat.getMainExecutor(context))


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