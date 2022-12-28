package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var firstTurn = SelectionType.CROSS
    private lateinit var binding: ActivityMainBinding

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTextForTV()
    }

    private fun setTextForTV() {
        when (firstTurn) {
            SelectionType.NOUGHT -> binding.textView.text = "Nought's Turn"
            SelectionType.CROSS -> binding.textView.text = "Cross's Turn"
        }
    }

    fun buttonClick(view: View) {
        if (counter == 9) {
            return
        }
        counter++
        val button = view as? Button ?: return
        if (firstTurn == SelectionType.CROSS) {
            button.text = "x"
            firstTurn = SelectionType.NOUGHT
        } else {
            button.text = "o"
            firstTurn = SelectionType.CROSS
        }
        checkResult()
        setTextForTV()
    }

    private fun checkResult() {
        var isFirstRowSame = false
        if (binding.a1.text.isNotBlank() && binding.a2.text.isNotBlank() && binding.a3.text.isNotBlank())
            isFirstRowSame =
                binding.a1.text.toString() == binding.a2.text.toString() && binding.a2.text.toString() == binding.a3.text.toString()
        val isSecondRowSame =
            binding.b1.text.toString() == binding.b2.text.toString() && binding.b2.text.toString() == binding.b3.text.toString()
        val isThirdRowSame =
            binding.c1.text.toString() == binding.c2.text.toString() && binding.c2.text.toString() == binding.c3.text.toString()

        val firstColumnSame =
            binding.a1.text.toString() == binding.b1.text.toString() && binding.b1.text.toString() == binding.c1.text.toString()
        val secondColumnSame =
            binding.a2.text.toString() == binding.b2.text.toString() && binding.b2.text.toString() == binding.c2.text.toString()
        val thirdColumnSame =
            binding.a3.text.toString() == binding.b3.text.toString() && binding.b3.text.toString() == binding.c3.text.toString()

        val isFirstDiagonalSame =
            binding.a1.text.toString() == binding.b2.text.toString() && binding.b2.text.toString() == binding.c3.text.toString()
        val isSecondDiagonalSame =
            binding.c1.text.toString() == binding.b2.text.toString() && binding.b2.text.toString() == binding.a3.text.toString()

        if (isFirstRowSame || isSecondRowSame || isThirdRowSame || firstColumnSame || secondColumnSame || thirdColumnSame || isFirstDiagonalSame || isSecondDiagonalSame) {
            Toast.makeText(this, "You have won...", Toast.LENGTH_SHORT).show()
        }
    }
}

enum class SelectionType {
    NOUGHT, CROSS
}

