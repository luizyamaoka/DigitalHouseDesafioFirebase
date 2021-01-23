package br.com.digitalhouse.desafiofirebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.digitalhouse.desafiofirebase.R
import br.com.digitalhouse.desafiofirebase.domain.Game
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val game = intent.getSerializableExtra("game") as Game

        toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        tvTitulo.text = game.name
        tvTitulo2.text = game.name
        tvAno.text = game.ano
        tvDescricao.text = game.descricao

        Glide.with(this).asBitmap()
            .load(game.image)
            .into(ivCover)
    }
}