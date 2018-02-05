package com.limahrc.kryptos.ciphers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.limahrc.kryptos.R
import com.limahrc.kryptos.copyToClipBoard
import com.limahrc.kryptos.makeToast
import vigenere

class Vigenere : AppCompatActivity() {

    private lateinit var inputMessageBox:   EditText
    private lateinit var outputMessageBox:  EditText
    private lateinit var keywordBox:        EditText
    private lateinit var buttonEncrypt:     Button
    private lateinit var buttonDecrypt:     Button
    private lateinit var buttonRotate:      Button
    private lateinit var buttonClear:       Button
    private lateinit var buttonCopy:        Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vigenere)

        setTitle(R.string.vigeneres_cipher)

        inputMessageBox     =   findViewById(R.id.inputVigenere)
        outputMessageBox    =   findViewById(R.id.outputVigenere)
        keywordBox          =   findViewById(R.id.keywordVigenere)
        buttonEncrypt       =   findViewById(R.id.btEncryptVigenere)
        buttonDecrypt       =   findViewById(R.id.btDecryptVigenere)
        buttonRotate        =   findViewById(R.id.btRotateVigenere)
        buttonClear         =   findViewById(R.id.btClearVigenere)
        buttonCopy          =   findViewById(R.id.btCopyVigenere)

        buttonEncrypt.setOnClickListener {
            actionCryptButtons(true)
        }

        buttonDecrypt.setOnClickListener {
            outputMessageBox.text.clear()
            actionCryptButtons(false)
        }

        buttonRotate.setOnClickListener {
            actionButtonRotate()
        }

        buttonClear.setOnClickListener {
            inputMessageBox.text.clear()
            keywordBox.text.clear()
            outputMessageBox.text.clear()
            makeToast(this,getString(R.string.cleaned))
        }

        buttonCopy.setOnClickListener {
            if (outputMessageBox.text.isNotEmpty()) {
                copyToClipBoard(this, getString(R.string.encrypted_message),
                        outputMessageBox.text.toString())
                makeToast(this, getString(R.string.copied))
            }
            else makeToast(this, getString(R.string.nothing_to_copy))
        }

    }

    private fun actionCryptButtons(encrypt: Boolean) {
        if (inputMessageBox.text.isNotEmpty()) {
            if (keywordBox.text.isNotEmpty()) {
                val message = inputMessageBox.text.toString()
                val keyword = keywordBox.text.toString()
                outputMessageBox.text.clear()
                outputMessageBox.setText(vigenere(message, keyword, encrypt))
            }
            else makeToast(this, getString(R.string.no_keyword))
        }
        else makeToast(this, R.string.empty_input.toString())
    }

    private fun actionButtonRotate() {
        val temp = inputMessageBox.text
        inputMessageBox.text = outputMessageBox.text
        outputMessageBox.text = temp
    }


}
