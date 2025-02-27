package com.BrasSec

import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


private val PERMISSION_ACCESIBILITY = 123
private var serviceIntent: Intent? = null
var OVERLAY_PERMISSION = 21
var finish = 0
var controle = 0

class MainActivity() : AppCompatActivity() {




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        if (isAccessibilityEnabled() == true) {
            val intentPlay = Intent().apply {
                setClassName("com.android.vending", "com.google.android.finsky.activities.MainActivity")
            }
            startActivityForResult(intentPlay, 100)
            finish()
        }

        setContentView(R.layout.layout_accessibility)
        var botao_next = findViewById<Button>(R.id.button1)
        var botao_back = findViewById<Button>(R.id.button2)

        botao_next.setOnClickListener() {


             if (isAccessibilityEnabled() == false) {

                  serviceIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                  startActivityForResult(serviceIntent!!, PERMISSION_ACCESIBILITY)
              }

                if (!Settings.canDrawOverlays(this)) {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + packageName))
                    val OVERLAY_PERMISSION_REQUEST_CODE = 1234
                    startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE)
                }

              else {
                  var intent= Intent(applicationContext, SecondActivity::class.java)
                  startActivity(intent)
                  finish = 1
              }
        }
        botao_back.setOnClickListener(){

            close()
        }

    }

   private fun close(){
        SystemClock.sleep(600)
       onBackPressedDispatcher.onBackPressed()

      // finish()
    }

    @SuppressLint("ServiceCast")
    private fun isAccessibilityEnabled(): Boolean? {
        var is_acess: Boolean? = false
        val accessibilityManager = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        val enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(
            AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
        for (enabledService in enabledServices) {
            val packageName = enabledService.resolveInfo.serviceInfo.packageName
            if (packageName == "com.BrasSec") {

                is_acess = true
            }
        }
        return is_acess }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PERMISSION_ACCESIBILITY) {

            if (isAccessibilityEnabled() == true && controle==0) {
                controle = 1
                var intent= Intent(applicationContext, SecondActivity::class.java)

                startActivity(intent)
            }

            else {
                Toast.makeText(applicationContext,"Ative a permissao" +
                        " de accessibilidade para continuar", Toast.LENGTH_LONG).show()
                finish = 1
            }
        }}
}
