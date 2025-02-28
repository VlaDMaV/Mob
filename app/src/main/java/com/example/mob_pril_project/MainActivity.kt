package com.example.mob_pril_project

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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
            if (text == "Hello World") {
                Toast.makeText(this, "User enter Hello World", Toast.LENGTH_SHORT).show()
                } else {
                label.text = text
            }
        }
    }
}