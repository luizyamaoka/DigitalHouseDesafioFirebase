package br.com.digitalhouse.desafiofirebase.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.desafiofirebase.domain.Game
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class GameViewModel() : ViewModel() {

    var db = FirebaseFirestore.getInstance()

    val games = MutableLiveData<ArrayList<Game>>()
    val response = MutableLiveData<String>()

    fun getGames() {
        viewModelScope.launch {
            db.collection("games")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val gamesList = arrayListOf<Game>()
                        for (document in task.result!!) {
                            val game = Game(
                                document.data["nome"] as String,
                                document.data["ano"] as String,
                                document.data["img"] as String,
                                document.data["descricao"] as String)
                            gamesList.add(game)
                        }
                        games.value = gamesList
                    } else {
                        Log.w("GameViewModel", "Error getting documents.", task.exception)
                    }
                }
//            games.value = arrayListOf(
//                    Game("teste1", "2020", "teste","descricao 1"),
//                    Game("teste2", "2019", "teste 2","descricao 2"),
//                    Game("teste1", "2020", "teste","descricao 1"),
//                    Game("teste2", "2019", "teste 2","descricao 2"),
//                    Game("teste1", "2020", "teste","descricao 1"),
//                    Game("teste2", "2019", "teste 2","descricao 2"),
//                    Game("teste1", "2020", "teste","descricao 1"),
//                    Game("teste2", "2019", "teste 2","descricao 2")
//            )

        }
    }

    fun saveGame(game: Game) {
        viewModelScope.launch {
            val obj: MutableMap<String, Any> = HashMap()
            obj["nome"] = game.name
            obj["ano"] = game.ano
            obj["image"] = game.image
            obj["descricao"] = game.descricao

            db.collection("games")
                .add(obj)
                .addOnSuccessListener { documentReference ->
                    response.value = "Sucesso"
                }
                .addOnFailureListener { e -> response.value = e.toString() }
        }
    }

}