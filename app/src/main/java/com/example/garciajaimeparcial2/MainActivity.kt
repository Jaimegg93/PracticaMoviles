package com.example.garciajaimeparcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculadoraImageView: ImageView = findViewById(R.id.calculadoraImageView)
        val botonImageView: Button = findViewById(R.id.loginImageView)
        val botonAudios: Button = findViewById(R.id.multimedia)

        calculadoraImageView.setOnClickListener {
            val intent = Intent(this, calculadora::class.java)
            startActivity(intent)
        }
        botonImageView.setOnClickListener {
            val intent = Intent(this, Log_In::class.java)
            startActivity(intent)
        }
        botonAudios.setOnClickListener {
            val intent = Intent(this, MultimediaVideo::class.java)
            startActivity(intent)
        }
    }
}