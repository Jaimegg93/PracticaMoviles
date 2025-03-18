package com.example.garciajaimeparcial2

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Multimedia : AppCompatActivity() {

    private lateinit var sp: SoundPool
    private var spLoaded: Boolean = false
    private var soundId: Int = 0
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_multimedia)

        // 1. Configuración del SoundPool
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        sp = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        // 2. Cargar el sonido
        soundId = sp.load(this, R.raw.aplausos, 1)
        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                spLoaded = true
                Toast.makeText(this, "Sonido cargado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al cargar el sonido", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Configurar el botón después de definir el layout
        val botonAudioCorto = findViewById<Button>(R.id.playSp)
        botonAudioCorto.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
            } else {
                Toast.makeText(this, "Sonido aún no cargado", Toast.LENGTH_SHORT).show()
            }
        }

        // 1. Inicializar el MediaPlayer con un archivo de audio de los
        // recursos
        this.mediaPlayer = MediaPlayer.create(this, R.raw.duhast)

        // 3.1 Configurar un listener para liberar recursos cuando la
        // reproducción termine
        this.mediaPlayer.setOnCompletionListener {
            this.mediaPlayer.release()
        }

        // 2.1 Iniciar la reproducción
        val botonPlay = findViewById<Button>(R.id.playMp)
        botonPlay.setOnClickListener {
            this.mediaPlayer.start();
        }
        // 2.2 Pausar la reproducción
        val botonPause = findViewById<Button>(R.id.pauseMp)
        botonPause.setOnClickListener {
            if (this.mediaPlayer.isPlaying) {
                this.mediaPlayer.pause()
            }
        }
        // 2.3. Detener la reproducción, prepararla e inicializarla
        val botonStop = findViewById<Button>(R.id.stopMp)
        botonStop.setOnClickListener {
            if (this.mediaPlayer.isPlaying) {
                this.mediaPlayer.stop()
                this.mediaPlayer.prepare()
                this.mediaPlayer.seekTo(0)
            }
        }
    }

    // 4. Liberación de recursos
    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized){
            this.mediaPlayer.release()
        }
        sp.release()
    }
}
