package com.carloscdev.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var responseText: TextView? = null
    var selectOperation: Boolean = false
    var firstNumber: String = ""
    var secondNumber: String = ""
    var currentOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Iniciando Caluladora")

        responseText = findViewById(R.id.first_text)

        findViewById<Button>(R.id.button_igual).setOnClickListener{
            if (selectOperation) {
                var intent = Intent(this, ResponseActivity::class.java)
                intent.putExtra("firstNumber", firstNumber)
                intent.putExtra("secondNumber", secondNumber)
                intent.putExtra("currentOperation", currentOperation)
                startActivity(intent)
            } else {
                println("Seleccione una operación para continuar")
            }
        }
    }
    // Obtiene el valor del botón y concatena
    fun getValue(view: View) {
        var button = view as Button
        var textButton = button.text.toString()
        var concatenar = ""
        if (!selectOperation) {
            concatenar = firstNumber + textButton
            firstNumber = validateCero(concatenar)
        } else {
            concatenar = secondNumber + textButton
            secondNumber = validateCero(concatenar)
        }
        printResponse()
    }

    // Obtiene la operación seleccionada y concatena
    fun getOperation(view: View) {
        var button = view as Button
        var currentIdOperation = button.getId()
        selectOperation = true
        if (currentIdOperation == R.id.button_multi) {
            currentOperation = "*"
        } else if (currentIdOperation == R.id.button_divi) {
            currentOperation = "/"
        } else if (currentIdOperation == R.id.button_resta) {
            currentOperation = "-"
        } else if (currentIdOperation == R.id.button_suma) {
            currentOperation = "+"
        }
        printResponse()
    }

    // Función que concatena el response para mostrar en pantalla
    fun printResponse() {
        responseText?.text = firstNumber + currentOperation + secondNumber
    }

    // Restaura  todos los valores a su estado inicial
    fun resetAll(view: View) {
        responseText?.text = "0"
        selectOperation = false
        firstNumber = ""
        secondNumber = ""
        currentOperation = ""
    }

    // Valida que no se ingresen ceros a la izquierda
    fun validateCero(str: String): String {
        var i = 0
        while(i < str.length && str[i] == '0') i++
        val sb = StringBuffer(str)
        sb.replace(0, i, "")
        return sb.toString()
    }
}