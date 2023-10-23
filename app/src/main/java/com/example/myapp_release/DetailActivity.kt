package com.example.myapp_release

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.example.myapp_release.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var hero: Hero? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Mengambil data Hero yang dikirim dari MainActivity
        hero = intent.getParcelableExtra<Hero>("HERO_EXTRA")

        // Menampilkan informasi Hero pada halaman detail
        hero?.let {
            binding.tvNameDetail.text = it.name
            binding.tvDescriptionDetail.text = it.description
            Glide.with(this)
                .load(it.photo)
                .into(binding.imgDetail)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                // Membuat teks yang akan dibagikan
                val shareText = "${hero?.name}\n\n${hero?.description}"

                // Membuat Intent untuk berbagi teks
                val shareIntent = ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setText(shareText)
                    .setChooserTitle("Bagikan informasi pahlawan")
                    .createChooserIntent()

                // Memulai aktivitas berbagi
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}