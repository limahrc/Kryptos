package com.limahrc.kryptos

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import com.limahrc.kryptos.ciphers.Atbash
import com.limahrc.kryptos.ciphers.Caesar
import com.limahrc.kryptos.ciphers.Vigenere

class CipherSelection : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cipher_selection)

        val buttonAtbash = findViewById<Button>(R.id.btAtbash)
        val buttonCaesar = findViewById<Button>(R.id.btCaesar)
        val buttonVigenere = findViewById<Button>(R.id.btVigenere)

        buttonAtbash.setOnClickListener {
            startActivity(Intent(this, Atbash::class.java))
        }

        buttonCaesar.setOnClickListener {
            startActivity(Intent(this, Caesar::class.java))
        }

        buttonVigenere.setOnClickListener {
            startActivity(Intent(this, Vigenere::class.java))
        }

    }

    override fun onBackPressed() {
        val alert = AlertDialog.Builder(this)
        alert.setMessage(getString(R.string.exit_ask))
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener {
            dialogInterface, i ->
            finish()
        })
        alert.setNegativeButton("No", null)
        alert.show()
    }


}
