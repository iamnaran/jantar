package com.iamnaran.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class ImageUtils(private val context: Context, private val dispatcher: CoroutineDispatcher) {

    suspend fun convertImageToBase64(uri: Uri): String? = withContext(dispatcher) {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return@withContext null
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()

        return@withContext bitmap?.let { compressAndEncode(it) }
    }

    private fun compressAndEncode(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream().apply {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, this) // Optimized compression
        }
        
        val byteArray = outputStream.toByteArray()
        outputStream.close()

        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}