package com.gary.beastmode.activitys


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gary.beastmode.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    fun showSnackBar(message: String) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this@BaseActivity,
                R.color.colorPrimary
            )
        )
        snackBar.show()
    }
    fun shareThisApp(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hey! Check out BEAST MODE in the play store")
        intent.putExtra(
            Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.gary.beastmode"
        )
        startActivity(Intent.createChooser(intent, "Share with"))
    }
}
