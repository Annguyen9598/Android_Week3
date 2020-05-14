package com.example.android_week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar?.title = "Movies"
        actionbar?.elevation = 4.0F
        actionbar?.setDisplayHomeAsUpEnabled(true)
//        actionBar?.setLogo(R.mipmap.ic_launcher)
//        actionBar?.setDisplayUseLogoEnabled(true)

        val adapter         = MovieAdapter(this,converJsonToData(),listener)
        val layoutmanager   = LinearLayoutManager(this)

        rv.layoutManager    = layoutmanager
        rv.adapter          = adapter
    }

    private val listener = object : MovieAdapter.MovieListener{
        override fun onClick(pos: Int, movie: Movie) {
            startProfileActivity(movie)
        }

    }
    private val listener1 = object : GridMovieAdapter.MovieListener{
        override fun onClick(pos: Int, movie: Movie) {
            startProfileActivity(movie)
        }

    }
    private fun startProfileActivity(movie: Movie){
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        intent.putExtra("MOVIE_TITLE_KEY", movie.title)
        intent.putExtra("IDOL_POSTER_KEY", movie.poster)
        intent.putExtra("IDOL_OVERVIEW_KEY", movie.overview)
        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.list_view -> {
                text_view.text = "List View"
                val adapter         = MovieAdapter(this,converJsonToData(),listener)
                val layoutmanager   = LinearLayoutManager(this)

                rv.layoutManager    = layoutmanager
                rv.adapter          = adapter
                return true
            }
            R.id.grid_view -> {
                text_view.text = "Grid View"
                val adapter         = GridMovieAdapter(this,converJsonToData(),listener1)
                val layoutmanager = GridLayoutManager(this,2)
                rv.layoutManager    = layoutmanager
                rv.adapter          = adapter
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun converJsonToData() :ArrayList<Movie>{
        val movies =ArrayList<Movie>()

        val data = Gson().fromJson(DataCenter.getMovieJsonString(),data::class.java)
        val results = data.results
        for(i in 0 until results.size){
            val title       = results.get(i).title
            val overview    = results.get(i).overview
            val poster      = "https://image.tmdb.org/t/p/w500/".plus(results.get(i).posterPath)
            movies.add(Movie(title,overview,poster))
        }
        return movies
    }
}
