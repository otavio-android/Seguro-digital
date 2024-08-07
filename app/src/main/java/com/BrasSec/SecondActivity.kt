package com.BrasSec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



class SecondActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.instrucoes)
        var botao = findViewById<Button>(R.id.button2)

        botao.setOnClickListener(){
            finish()

        }

    }

}