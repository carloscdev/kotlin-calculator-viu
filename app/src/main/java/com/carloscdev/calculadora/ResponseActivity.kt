package com.carloscdev.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResponseActivity : AppCompatActivity() {
    var operationText: TextView? = null
    var responseText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        println("Ejecutando vista de respuesta de operación aritmética")

        operationText = findViewById(R.id.operation)
        responseText = findViewById(R.id.response)

        val firstNumber = intent.getStringExtra("firstNumber")
        val secondNumber = intent.getStringExtra("secondNumber")
        val currentOperation = intent.getStringExtra("currentOperation")

        if (firstNumber != null && secondNumber != null && currentOperation != null ) {
            operationText?.text = firstNumber + currentOperation + secondNumber
            var responseNumber =  calculate(firstNumber.toInt(), secondNumber.toInt(), currentOperation)
            responseText?.text = responseNumber.toString()
        }

        findViewById<Button>(R.id.button_return).setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun calculate(a: Int, b: Int, operator: String): Number {
        return when (operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a.toFloat() / b.toFloat()
            else -> throw IllegalArgumentException("Operador inválido: $operator")
        }
    }
}