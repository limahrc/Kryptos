package com.limahrc.kryptos.ciphers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import atbash
import com.limahrc.kryptos.R
import com.limahrc.kryptos.copyToClipBoard
import com.limahrc.kryptos.makeToast

/**
 * Created by erick on 05/02/18.
 */

class Atbash : AppCompatActivity() {

    private lateinit var buttonEncrypt:     Button
    private lateinit var buttonDecrypt:     Button
    private lateinit var inputMessageBox:   EditText
    private lateinit var outputMessageBox:  EditText
    private lateinit var spinnerOffset:     Spinner
    private lateinit var buttonRotate:      Button
    private lateinit var buttonClear:       Button
    private lateinit var buttonCopy:        Button
    private lateinit var labelOffset:       TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caesar)

        setTitle(R.string.atbash_cipher)

        buttonEncrypt       =   findViewById(R.id.btEncryptCaesar)
        buttonDecrypt       =   findViewById(R.id.btDecryptCaesar)
        inputMessageBox     =   findViewById(R.id.inputCaesar)
        outputMessageBox    =   findViewById(R.id.outputCaesar)
        spinnerOffset       =   findViewById(R.id.spnKeyCaesar)
        buttonRotate        =   findViewById(R.id.btRotateCaesar)
        buttonClear         =   findViewById(R.id.btClearCaesar)
        buttonCopy          =   findViewById(R.id.btCopyCaesar)
        labelOffset         =   findViewById(R.id.txtOffsetCaesar)

        spinnerOffset.visibility = View.INVISIBLE
        labelOffset.visibility = View.INVISIBLE

        buttonEncrypt.setOnClickListener {
            actionCryptButtons(true)
        }

        buttonDecrypt.setOnClickListener {
            actionCryptButtons(false)
        }

        buttonClear.setOnClickListener {
            inputMessageBox.text.clear()
            outputMessageBox.text.clear()
            makeToast(this, getString(R.string.cleaned))
        }

        buttonCopy.setOnClickListener {
            if (outputMessageBox.text.isNotEmpty()) {
                copyToClipBoard(this, getString(R.string.encrypted_message),
                        outputMessageBox.text.toString())
                makeToast(this, getString(R.string.copied))
            }
            else makeToast(this, getString(R.string.nothing_to_copy))
        }

        buttonRotate.setOnClickListener {
            val temp = inputMessageBox.text
            inputMessageBox.text = outputMessageBox.text
            outputMessageBox.text = temp
        }

        buttonClear.setOnClickListener {
            inputMessageBox.text.clear()
            outputMessageBox.text.clear()
            makeToast(this, getString(R.string.cleaned))
        }

    }

    private fun actionCryptButtons(encrypt: Boolean){
        if (inputMessageBox.text.isNotEmpty()){
            val message = inputMessageBox.text.toString()
            outputMessageBox.text.clear()
            outputMessageBox.setText(atbash(message))
        }
        else makeToast(this, getString(R.string.empty_input))
    }
}