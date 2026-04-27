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

class forgot_password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // 1. Kenalin komponen UI
        val headerContainer = findViewById<LinearLayout>(R.id.headerContainer)
        val forgotSheet = findViewById<LinearLayout>(R.id.forgotSheet)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)
        val btnSendReset = findViewById<Button>(R.id.btnSendReset)
        val etResetEmail = findViewById<EditText>(R.id.etResetEmail)

        // 2. ANIMASI dengan Jeda
        Handler(Looper.getMainLooper()).postDelayed({

            // Animasi Teks Header meluncur ke bawah
            headerContainer.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(800)
                .setInterpolator(DecelerateInterpolator())
                .start()

            // Animasi Sheet Form naik ke atas
            forgotSheet.animate()
                .translationY(0f)
                .alpha(1f)
                .setDuration(800)
                .setStartDelay(100)
                .start()

        }, 200)

        // 3. Tombol kembali ke Login (Dengan animasi reverse)
        tvBackToLogin.setOnClickListener {
            // Animasi Reverse (Tutup Tirai)
            headerContainer.animate().translationY(-200f).alpha(0f).setDuration(400).start()
            forgotSheet.animate().translationY(800f).alpha(0f).setDuration(400).start()

            // Tunggu 0.4 detik (400ms) sampai animasi kelar, baru pindah
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, login::class.java)
                startActivity(intent)

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

                finish()
            }, 400)
        }

        // 4. Tombol kirim email reset (Validasi)
        btnSendReset.setOnClickListener {
            val email = etResetEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Fungsi Firebase sendPasswordResetEmail() masuk sini nanti
            Toast.makeText(this, "Link reset siap dikirim ke $email", Toast.LENGTH_LONG).show()
        }
    }
}