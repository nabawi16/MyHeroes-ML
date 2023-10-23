package com.example.myapp_release

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){
    private val splashTimeOut: Long = 500 // Durasi tampilan splash (dalam milidetik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Mengambil referensi ke elemen gambar yang akan dianimasikan
        val splashImage = findViewById<ImageView>(R.id.splash_image)

        // Memuat animasi dari file XML animasi
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Animasi dimulai
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Handler untuk mengganti ke Activity utama setelah beberapa detik
                Handler().postDelayed({
                    val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }, splashTimeOut)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        splashImage.startAnimation(animation)
    }
}