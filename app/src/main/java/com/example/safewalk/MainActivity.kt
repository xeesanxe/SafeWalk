package com.example.safewalk

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pastikan nama ID di sini (btnPanic, dll) sama persis dengan yang ada di XML tadi
        val btnPanic = findViewById<CardView>(R.id.btnPanic)
        val cardTracking = findViewById<CardView>(R.id.cardTracking)
        val cardContacts = findViewById<CardView>(R.id.cardContacts)

        btnPanic.setOnClickListener {
            Toast.makeText(this, "Sinyal SOS Terkirim!", Toast.LENGTH_SHORT).show()
        }

        cardTracking.setOnClickListener {
            Toast.makeText(this, "Membuka Peta Pelacakan...", Toast.LENGTH_SHORT).show()
        }

        cardContacts.setOnClickListener {
            Toast.makeText(this, "Membuka Daftar Kontak...", Toast.LENGTH_SHORT).show()
        }
    }
}