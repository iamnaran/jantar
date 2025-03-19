package com.iamnaran.camera.camera

data class PreviewMedia(val uriString: String, val previewMediaStatus: PreviewMediaStatus)

public enum class PreviewMediaStatus {
    UNKNOWN,
    SUCCESS,
    ERROR
}