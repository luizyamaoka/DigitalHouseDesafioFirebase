package br.com.digitalhouse.desafiofirebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.digitalhouse.desafiofirebase.R
import kotlinx.android.synthetic.main.activity_new_game.*

class NewGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        floatingActionButton3.setOnClickListener {
            // TODO: pega imagem arquivo
        }

        btnSave.setOnClickListener {
            inputName.text.toString()
            inputAno.text.toString()
            inputDescricao.text.toString()
            // TODO: salva
        }
    }
}