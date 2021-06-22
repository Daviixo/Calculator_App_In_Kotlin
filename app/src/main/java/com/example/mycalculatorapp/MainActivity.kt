package com.example.mycalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){

        tvInput.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View){

        tvInput.text = ""
        lastNumeric = false
        lastDot = false

    }

    fun onDecimalPoint(view: View){

        if (lastNumeric && !lastDot){

            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    fun onEqual(view: View){

        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {

                //Checking if input value is negative

                if(tvValue.startsWith("-")){

                prefix = "-"
                    tvValue = tvValue.substring(1)

                }

                if(tvValue.contains("-")){

                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){

                        one = prefix + one

                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                // This is for +++++++ operations

                }else if(tvValue.contains("+")){

                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){

                        one = prefix + one

                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                // This is for ////////////// operations

                }else if(tvValue.contains("/")){

                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){

                        one = prefix + one

                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                // This is for ********* operations

                }else if(tvValue.contains("*")){

                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){

                        one = prefix + one

                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }

            }catch(e: ArithmeticException){

                e.printStackTrace()
            }

        }

    }

    fun onOperator(view: View){

        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){

            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false

        }

    }

    fun onDropLast(view: View){

        var tvNewValue : String = tvInput.text.toString()

        tvNewValue = tvNewValue.dropLast(1)

        tvInput.text = tvNewValue
    }

    //Toast.makeText(applicationContext, TEXT GOES HERE,Toast.LENGTH_SHORT).show()

    private fun removeZeroAfterDot(result: String) : String{

        var value = result
        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }

    private fun isOperatorAdded(value: String) : Boolean {

        return if (value.startsWith("-")){
            false

        }else{
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")

        }

    }

}