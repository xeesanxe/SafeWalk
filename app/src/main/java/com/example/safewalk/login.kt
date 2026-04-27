package com.example.safewalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.TextView

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Kenalin semua komponen (Cukup sekali aja biar nggak conflict)
        val loginSheet = findViewById<LinearLayout>(R.id.loginSheet)
        val splashText = findViewById<TextView>(R.id.splashText)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)

        // 2. Animasi Masuk (Splash ke Form Login)
        Handler(Looper.getMainLooper()).postDelayed({
            splashText.animate().alpha(0f).setDuration(500).start()
            loginSheet.animate().translationY(0f).setDuration(800).start()
        }, 1500)

        // 3. Klik Sign Up dengan Animasi Tutup (Reverse)
        tvSignUp.setOnClickListener {
            // Form turun dulu sebelum pindah
            loginSheet.animate().translationY(1000f).alpha(0f).setDuration(400).start()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, signup::class.java)
                startActivity(intent)
                // Efek pudar antar activity
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }, 400)
        }

        // 4. Klik Forgot Password dengan Animasi Tutup (Reverse)
        tvForgotPassword.setOnClickListener {
            // Form turun dulu sebelum pindah
            loginSheet.animate().translationY(1000f).alpha(0f).setDuration(400).start()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, forgot_password::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }, 400)
        }
    }
}