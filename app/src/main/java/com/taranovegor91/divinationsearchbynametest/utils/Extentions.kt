package com.taranovegor91.notesdemoapplication.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.navigation.NavController
import com.taranovegor91.divinationsearchbynametest.R
import java.util.*

fun Context.showToastLong(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.shareText(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

