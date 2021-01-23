package br.com.digitalhouse.desafiofirebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.digitalhouse.desafiofirebase.R
import br.com.digitalhouse.desafiofirebase.adapter.GameAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val gameAdapter = GameAdapter()
    private val layoutManager = GridLayoutManager(this, 2)

    private val gameViewModel : GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvGames.adapter = gameAdapter
        rvGames.layoutManager = layoutManager

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NewGameActivity::class.java)
            startActivity(intent)
        }

        gameViewModel.games.observe(this, Observer {
            gameAdapter.addGames(it)
        })


        gameViewModel.getGames()
    }
}