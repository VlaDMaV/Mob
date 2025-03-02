package com.example.mob_pril_project

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val label = findViewById<TextView>(R.id.label)
        val userData: EditText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button3)
        button.setOnClickListener {
            val text = userData.text.toString().trim()
            if (text == "клянусь" || text == "Клянусь") {
                showFullScreenImageDialog(R.drawable.klinus, R.raw.thisme)
            } else {
                Toast.makeText(this, "User enter $text", Toast.LENGTH_SHORT).show()
                label.text = text
            }
        }
    }

    private fun showFullScreenImageDialog(imageResId: Int, musicResId: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.full_screen_image_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT)
        val fullScreenImageView = dialog.findViewById<ImageView>(R.id.fullScreenImageView)
        val closeButton = dialog.findViewById<Button>(R.id.closeButton)
        fullScreenImageView.setImageResource(imageResId)

        // Воспроизведение музыки
        mediaPlayer = MediaPlayer.create(this, musicResId)
        mediaPlayer?.start()

        closeButton.setOnClickListener {
            dialog.dismiss()
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
        dialog.setOnDismissListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}