package com.BrasSec

import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class MainActivity() : AppCompatActivity() {
    private val PERMISSION_ACCESIBILITY = 123
    private var serviceIntent: Intent? = null
    var OVERLAY_PERMISSION = 21
    var finish = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_accessibility)
        var botao_next = findViewById<Button>(R.id.button1)
        var botao_back = findViewById<Button>(R.id.button2)

        botao_next.setOnClickListener() {


             if (isAccessibilityEnabled() == false) {

                  serviceIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                  startActivityForResult(serviceIntent!!, PERMISSION_ACCESIBILITY)
              }

            /*    if (!Settings.canDrawOverlays(this)) {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + packageName))
                    val OVERLAY_PERMISSION_REQUEST_CODE = 1234
                    startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE)
                }*/

              else {
                  var intent= Intent(applicationContext, SecondActivity::class.java)
                  startActivity(intent)
                  finish = 1
              }
        }

        botao_back.setOnClickListener(){
          finish()
        }

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

            if (isAccessibilityEnabled() == true) {
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
