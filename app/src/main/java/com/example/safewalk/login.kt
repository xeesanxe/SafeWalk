package com.example.safewalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Kenalin semua komponen UI
        val loginSheet = findViewById<LinearLayout>(R.id.loginSheet)
        val splashText = findViewById<TextView>(R.id.splashText)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin) // Pastikan ID di XML-nya 'btnLogin'

        // 2. Animasi Masuk (Splash ke Form Login saat aplikasi dibuka)
        Handler(Looper.getMainLooper()).postDelayed({
            splashText.animate().alpha(0f).setDuration(500).start()
            loginSheet.animate().translationY(0f).setDuration(800).start()
        }, 1500)

        // 3. Logika Klik Tombol LOGIN (Masuk ke Homepage)
        btnLogin.setOnClickListener {
            // Efek tutup tirai: Form turun ke bawah dulu
            loginSheet.animate().translationY(1000f).alpha(0f).setDuration(400).start()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

                // Bakar halaman login dari memori biar user nggak bisa 'Back' lagi ke sini
                finish()
            }, 400)
        }

        // 4. Klik Sign Up (Pindah ke halaman Signup)
        tvSignUp.setOnClickListener {
            loginSheet.animate().translationY(1000f).alpha(0f).setDuration(400).start()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, signup::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }, 400)
        }

        // 5. Klik Forgot Password (Pindah ke halaman Reset)
        tvForgotPassword.setOnClickListener {
            loginSheet.animate().translationY(1000f).alpha(0f).setDuration(400).start()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, forgot_password::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }, 400)
        }
    }
}