package com.part1.changelength

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.part1.changelength.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var inputNumber : Int = 0
    var cmToMeter = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val inputTextViewParam = binding.inputEditTextParams
        val inputTextView = binding.inputEditText
        val outputTextViewReturn = binding.outputEditTextReturn
        val btnSwap = binding.swapImageBtn

        inputTextView.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()){
                0
            } else{
                text.toString().toInt()
            }
            if (cmToMeter){
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
        btnSwap.setOnClickListener {
            cmToMeter = cmToMeter.not()
            if (cmToMeter){
                inputTextViewParam.text = "cm"
                outputTextViewReturn.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            }
            else{
                inputTextViewParam.text = "m"
                outputTextViewReturn.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToMeter", cmToMeter)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToMeter = savedInstanceState.getBoolean("cmToMeter")
        binding.inputEditTextParams.text = if (cmToMeter) "cm" else "m"
        binding.outputEditTextReturn.text = if (cmToMeter) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}