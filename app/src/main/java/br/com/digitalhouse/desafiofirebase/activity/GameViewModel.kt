package br.com.digitalhouse.desafiofirebase.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.desafiofirebase.domain.Game
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {

    val games = MutableLiveData<ArrayList<Game>>()

    fun getGames() {
        viewModelScope.launch {
            games.value = arrayListOf(
                    Game("teste1", "2020", "teste","descricao 1"),
                    Game("teste2", "2019", "teste 2","descricao 2"),
                    Game("teste1", "2020", "teste","descricao 1"),
                    Game("teste2", "2019", "teste 2","descricao 2"),
                    Game("teste1", "2020", "teste","descricao 1"),
                    Game("teste2", "2019", "teste 2","descricao 2"),
                    Game("teste1", "2020", "teste","descricao 1"),
                    Game("teste2", "2019", "teste 2","descricao 2")
            )

        }
    }

}