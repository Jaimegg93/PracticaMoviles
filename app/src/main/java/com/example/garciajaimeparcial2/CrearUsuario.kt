package com.example.garciajaimeparcial2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CrearUsuario : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText1: EditText
    private lateinit var passwordEditText2: EditText
    private lateinit var crearUsuarioButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_usuario)

        crearUsuarioButton = findViewById(R.id.crear)

        usernameEditText = findViewById(R.id.username)
        passwordEditText1 = findViewById(R.id.password1)
        passwordEditText2 = findViewById(R.id.password2)

        crearUsuarioButton.setOnClickListener { showCreaUsuarioDialog()}

    }


    private fun showCreaUsuarioDialog() {
        val username = usernameEditText.text.toString()
        val password1 = passwordEditText1.text.toString()
        val password2 = passwordEditText2.text.toString()

        if (password1 != password2) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val userDB = UserDatabaseHelper(this)
        AlertDialog.Builder(this)
            .setTitle("Crear usuario")
            .setMessage("¿Estas seguro de que quieres crear este usuario?")
            .setPositiveButton("Sí") { _, _ ->
                val creado = userDB.insertUser(username,password1)
                if (creado) {
                    Log.d("CrearUsuarioActivity", "Usuario creado: $username:")
                    Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Log_In::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("No", null)
            .create().show()
    }
}