package com.example.garciajaimeparcial2

import android.app.AlertDialog
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SoundPool
    private var spLoaded: Boolean = false
    private var soundId: Int = 0
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calculadoraImageView: ImageView = findViewById(R.id.calculadoraImageView)
        val botonImageView: Button = findViewById(R.id.loginImageView)
        val botonAudios: Button = findViewById(R.id.multimedia)


        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        sp = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        soundId = sp.load(this, R.raw.aplausos, 1)
        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                spLoaded = true
                Toast.makeText(this, "Sonido cargado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al cargar el sonido", Toast.LENGTH_SHORT).show()
            }
        }
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


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Mi Aplicación")
        toolbar.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }
        setActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu_sonidos)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sonidos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ReproducirSonido -> {
                if (spLoaded) {
                    sp.play(soundId, 1f, 1f, 0, 0, 1f)
                } else {
                    Toast.makeText(this, "Sonido aún no cargado", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.ReproducirVideo -> {
                val intent = Intent(this, MultimediaVideo::class.java)
                startActivity(intent)
            }
            R.id.ReproducirAudio -> {
                val intent = Intent(this, Multimedia::class.java)
                startActivity(intent)
            }
            R.id.Quitar -> {
                mostrarDialogoSalir()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarDialogoSalir() {
            AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("Se va a cerrar la aplicación, ¿está seguro de querer salir?")
                .setPositiveButton("Sí") { _, _ ->
                    finish()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
    }
}