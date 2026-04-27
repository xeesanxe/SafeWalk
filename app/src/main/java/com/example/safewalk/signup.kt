package com.example.safewalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // 1. Kenalin semua komponen UI ke Kotlin
        val headerContainer = findViewById<LinearLayout>(R.id.headerContainer) // Bungkusan teks baru
        val signupSheet = findViewById<LinearLayout>(R.id.signupSheet)
        val tvLogIn = findViewById<TextView>(R.id.tvLogIn)

        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // 2. Animasi (DIBUNGKUS HANDLER BIAR ADA JEDA)
        Handler(Looper.getMainLooper()).postDelayed({
            // Animasi Teks Header (Meluncur turun dari atas)
            headerContainer.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(800)
                .setInterpolator(DecelerateInterpolator()) // Biar berhentinya smooth
                .start()

            // Animasi Sheet Form (Naik dari bawah)
            signupSheet.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(800)
                .setStartDelay(100) // Jeda 0.1 detik biar efeknya bergantian sama header
                .start()
        }, 200) // Jeda 0.2 detik (200ms) sebelum animasi mulai jalan biar layar ngerender dulu

        // 3. Logika Tombol "Log In" (Balik ke halaman login dengan animasi)
        tvLogIn.setOnClickListener {
            // Animasi Reverse (Tutup Tirai)
            headerContainer.animate().translationY(-200f).alpha(0f).setDuration(400).start()
            signupSheet.animate().translationY(800f).alpha(0f).setDuration(400).start()

            // Tunggu 0.4 detik (400ms) sampai animasi di atas kelar, baru pindah halaman
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, login::class.java)
                startActivity(intent)

                // Opsional: Bikin transisi antar halamannya jadi mode pudar (fade)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

                finish()
            }, 400)
        }

        // 4. LOGIKA TOMBOL "CREATE ACCOUNT" (Validasi Input)
        btnRegister.setOnClickListener {
            // Ambil teks yang diketik user, dan hilangkan spasi berlebih pakai trim()
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Pengecekan 1: Nggak boleh ada kolom yang kosong
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                // Munculin pesan peringatan (Toast)
                Toast.makeText(this, "Semua kolom harus diisi ya!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Berhenti di sini, jangan lanjut ke bawah
            }

            // Pengecekan 2: Password minimal 6 karakter (syarat wajib Firebase)
            if (password.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kalau semua pengecekan lolos:
            Toast.makeText(this, "Validasi sukses! Siap kirim ke Firebase...", Toast.LENGTH_SHORT).show()

            // TODO: Nanti fungsi Firebase Authentication ditaruh di sini
        }
    }
}