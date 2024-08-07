package com.BrasSec

import android.os.AsyncTask
import android.util.Log
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class EnviarEmailTask: AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg params: Unit) {

        Log.d("brassec", "VEIO PRA SENDEMAIL")
        // Configurações do servidor de e-mail SMTP do seu provedor de e-mail
        val props = Properties()
        props["mail.smtp.host"] = "sandbox.smtp.mailtrap.io" // Substitua pelo servidor SMTP do seu provedor
        props["mail.smtp.port"] = " 465" // Geralmente, a porta SMTP é 587 ou 465, dependendo do provedor
        props["mail.smtp.auth"] = "true" // A autenticação é necessária
        props["mail.smtp.starttls.enable"] = "true" // Use TLS, se necessário

        // Configuração das credenciais de e-mail do remetente
        val username = "tcv13i+etj97b51caza4@sharklasers.com"
        val password = "Babalu123" // Sua senha

        // Cria uma sessão de e-mail
        val session = Session.getInstance(props)

        try {
            Log.d("brassec", "VEIO PRA try")
            // Cria a mensagem de e-mail
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username)) // Seu endereço de e-mail
            message.setRecipient(Message.RecipientType.TO, InternetAddress("fb1efb17be-ea75981@inbox.mailtrap.io")) // Endereço de e-mail do destinatário
            message.subject = "Valor"
            message.setText("Essa e uma nensagem de teste $$$$$")

            // Conecta-se ao servidor de e-mail e envia o e-mail
            val transport = session.getTransport("smtp")
            transport.connect("sandbox.smtp.mailtrap.io", username, password)
            transport.sendMessage(message, message.allRecipients)
            transport.close()
            // onInterrupt()

        } catch (e: MessagingException) {
            Log.d("brassec", "veio pra catch")
            e.printStackTrace()
  }

    }
}



