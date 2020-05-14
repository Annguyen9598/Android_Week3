package com.example.android_week4

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolbar1 = findViewById(R.id.toolbar1) as Toolbar?
        setSupportActionBar(toolbar1)

        val actionbar = supportActionBar
        actionbar?.title = "Movies"
        actionbar?.elevation = 4.0F
        actionbar?.setDisplayHomeAsUpEnabled(true)
        toolbar1?.setOnClickListener { object : View.OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }

        }}
        getAndDisplayData()
    }

    private fun getAndDisplayData() {
        val data = intent.extras

        if (data != null) {
            val title = data.getString("MOVIE_TITLE_KEY")
            val overview = data.getString("IDOL_OVERVIEW_KEY")
            val poster = data.getString("IDOL_POSTER_KEY")
            textviewtitlemovie.text = title
            textviewoverview.text   = overview
            tvName.text             = title
            tvdescription .text     = overview
            Glide.with(this)
                .load(poster)
                .centerCrop()
                .into(imageviewbackgroundmovie)
            Glide.with(this)
                .load(poster)
                .centerCrop()
                .into(imageviewposter)
        }
    }
}