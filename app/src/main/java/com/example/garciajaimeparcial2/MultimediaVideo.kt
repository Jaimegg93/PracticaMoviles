package com.example.garciajaimeparcial2

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MultimediaVideo : AppCompatActivity() {
    // Variable para que al pausar no reinicie el vídeo
    private var iniciar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_multimedia_video)

        val vv = findViewById<VideoView>(R.id.videoView)

        // Ruta de un vídeo en res/raw
        val ruta = "android.resource://" + packageName + "/" + R.raw.video_messi



        // Ruta de un vídeo en internet
        //val ruta = "http://www.youtube.com/embed/yS_p_ICLUAw?autoplay=1&vq=small"
        val botonPlay = findViewById<Button>(R.id.buttonPlay)
        botonPlay.setOnClickListener {
            if (this.iniciar) {
                // Si la ruta es de un recurso se podría utilizar:
                 vv.setVideoPath(ruta) //en lugar de setVideoURI
                //vv.setVideoURI(Uri.parse(ruta))
                val mediaController = MediaController(this)
                mediaController.setAnchorView(vv)
                vv.setMediaController(mediaController)
            }
            vv.start()
        }
        val botonPause = findViewById<Button>(R.id.buttonPause)
        botonPause.setOnClickListener {
            this.iniciar = false
            vv.pause()
        }
        val botonStop = findViewById<Button>(R.id.buttonStop)
        botonStop.setOnClickListener {
            this.iniciar = true
            vv.stopPlayback()
            vv.seekTo(0)
        }
    }
}