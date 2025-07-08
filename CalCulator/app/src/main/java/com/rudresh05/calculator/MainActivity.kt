package com.rudresh05.calculator

import android.R.attr.text
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rudresh05.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val hapticClickListener = View.OnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAllClear.setOnClickListener {
            binding.inputText.text=""
            binding.outputText.text=""
        }

        binding.btn0.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("0")
        }
        binding.btn1.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("1")
        }
        binding.btn2.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("2")
        }
        binding.btn3.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("3")
        }
        binding.btn4.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("4")
        }
        binding.btn5.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("5")
        }
        binding.btn6.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("6")
        }
        binding.btn7.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("7")
        }
        binding.btn8.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("8")
        }
        binding.btn9.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append("9")
        }

        binding.btnPlus.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            val current = binding.inputText.text.toString()
            if(binding.inputText.text.toString().isEmpty()  || current.last() =='+' || current.last() =='.') {
                Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleOperator("+")
        }
        binding.btnMinus.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            val current = binding.inputText.text.toString()
            if(binding.inputText.text.toString().isEmpty()  || current.last() =='-' || current.last() =='.') {
                Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleOperator("-")
        }
        binding.btnMult.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            val current = binding.inputText.text.toString()
            if(binding.inputText.text.toString().isEmpty()  || current.last() =='*' || current.last() =='.') {
                Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleOperator("*")
        }
        binding.btnDivide.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            val current = binding.inputText.text.toString()
            if(binding.inputText.text.toString().isEmpty()  || current.last() =='/' || current.last() =='.') {
                Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleOperator("/")
        }
        binding.btnDot.setOnClickListener {
            val current = binding.inputText.text.toString()
            if(binding.inputText.text.toString().isEmpty() || current.last() =='.') {
                Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val lastPart = current.takeLastWhile { it.isDigit() || it=='.' }
            if(lastPart.contains('.'))return@setOnClickListener
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            binding.inputText.append(".")
        }
        binding.btnStartBrackate.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            handleBrackets( "(")
        }
        binding.btnEndBrackate.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            handleBrackets(")")
        }

        binding.btnDel.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            if(binding.inputText.text.toString().isEmpty()) {
                Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var currenText = binding.inputText.text.toString()
            if(currenText.isNotEmpty()){
                var newText = currenText.substring(0,currenText.length-1)
                binding.inputText.setText(newText)

            }
        }
    binding.btnEqual.setOnClickListener {
        it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        if(binding.inputText.text.toString().isEmpty()) {
            Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        var expression = ExpressionBuilder(binding.inputText.text.toString()).build()
        var result = expression.evaluate()
        var longResult = result.toLong()

        if(result == longResult.toDouble()){
            binding.outputText.text = longResult.toString()
        }
        else{
            binding.outputText.text = result.toString()
        }
    }


    }
    fun handleOperator(operator : String){
        val currenText = binding.inputText.text.toString()
        if(currenText.isEmpty()){
            Toast.makeText(this, "plese enter number", Toast.LENGTH_SHORT).show()
        }
        val lastText = currenText.last()
        if(lastText=='(') {return}
        if(lastText in listOf('+','-','*','/')){
         val updateText = currenText.dropLast(1)+operator
            binding.inputText.setText(updateText)
        }
        else{
            binding.inputText.append(operator)
        }

    }
    fun handleBrackets(bracket : String){
        val currenText = binding.inputText.text.toString()
        val openCount = currenText.count { it == '(' }
        val closeCount = currenText.count{it ==')'}
        if(currenText.isEmpty() || currenText.last() in listOf('+','-','*','/',')')){
            binding.inputText.append("(")
        }
        else if(bracket ==")"){

            if(openCount > closeCount && currenText.isNotEmpty() && currenText.last() !in listOf('+','-','*','/','(')){
                binding.inputText.append(")")

            }
        }
    }
}


