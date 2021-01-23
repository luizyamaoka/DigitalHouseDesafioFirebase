package br.com.digitalhouse.desafiofirebase.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.digitalhouse.desafiofirebase.R
import br.com.digitalhouse.desafiofirebase.domain.Game
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_new_game.*
import java.sql.Timestamp
import java.text.SimpleDateFormat

class NewGameActivity : AppCompatActivity() {

    lateinit var alertDialog : AlertDialog
    lateinit var storageReference: StorageReference
    private val CODE_IMG = 1000

    private val sdf = SimpleDateFormat("yyyyMMddHHmmss")

    private var imageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        floatingActionButton3.setOnClickListener {
            setIntent()
        }

        btnSave.setOnClickListener {
            val game = Game(
                inputName.text.toString(),
                inputAno.text.toString(),
                imageUrl,
                inputDescricao.text.toString())
            // TODO: salva
        }
    }

    fun config() {
        alertDialog = SpotsDialog.Builder().setContext(this).build()
        val timestamp = Timestamp(System.currentTimeMillis())
        val timestampString = sdf.format(timestamp)
        storageReference = FirebaseStorage.getInstance().getReference(timestampString)
    }

    fun setIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "captura imagem"), CODE_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMG) {
            alertDialog.show()
            val uploadTask = storageReference.putFile(data!!.data!!)
            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Chegando", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val url = downloadUri!!.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    imageUrl = url
                    Log.i("Link direto", url)
                    alertDialog.dismiss()
                }
            }
        }
    }
}