package com.example.firebase
import android.graphics.Bitmap
data class Posts(
    val postId: Char,
    val date :Char,
    val Description: String,
    val displayName : String,
    val postImage: Bitmap,
    val profilePhoto: Bitmap,
    val Title: String,
    val userId : String,
    )
