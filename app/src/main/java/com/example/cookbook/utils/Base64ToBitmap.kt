package com.example.cookbook.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun base64ToBitmap(base64String: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(base64String, Base64.NO_WRAP)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        e.printStackTrace()
        println("Error decoding Base64: ${e.message}")
        null
    }
}

