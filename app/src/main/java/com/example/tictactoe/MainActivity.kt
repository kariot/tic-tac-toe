package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
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
        val button = view as? Button ?: return
        //clicked on a button which was clicked earlier
        if (button.text.isNotBlank()) {
            return
        }
        if (counter == 9) {
            return
        }
        counter++
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
        binding.apply {
            var isFirstRowSame = false
            if (a1.text.isNotBlank() && a2.text.isNotBlank() && a3.text.isNotBlank())
                isFirstRowSame =
                    a1.text.toString() == a2.text.toString() && a2.text.toString() == a3.text.toString()
            var isSecondRowSame = false
            if (b1.text.isNotBlank() && b2.text.isNotBlank() && b3.text.isNotBlank())
                isSecondRowSame =
                    b1.text.toString() == b2.text.toString() && b2.text.toString() == b3.text.toString()
            var isThirdRowSame = false
            if (c1.text.isNotBlank() && c2.text.isNotBlank() && c3.text.isNotBlank())
                isThirdRowSame =
                    c1.text.toString() == c2.text.toString() && c2.text.toString() == c3.text.toString()
            var firstColumnSame = false
            if (a1.text.isNotBlank() && b1.text.isNotBlank() && c1.text.isNotBlank())
                firstColumnSame =
                    a1.text.toString() == b1.text.toString() && b1.text.toString() == c1.text.toString()
            var secondColumnSame = false
            if (a2.text.isNotBlank() && b2.text.isNotBlank() && c2.text.isNotBlank())
                secondColumnSame =
                    a2.text.toString() == b2.text.toString() && b2.text.toString() == c2.text.toString()
            var thirdColumnSame = false
            if (a3.text.isNotBlank() && b3.text.isNotBlank() && c3.text.isNotBlank())
                thirdColumnSame =
                    a3.text.toString() == b3.text.toString() && b3.text.toString() == c3.text.toString()
            var isFirstDiagonalSame = false
            if (a1.text.isNotBlank() && b2.text.isNotBlank() && c3.text.isNotBlank())
                isFirstDiagonalSame =
                    a1.text.toString() == b2.text.toString() && b2.text.toString() == c3.text.toString()
            var isSecondDiagonalSame = false
            if (c1.text.isNotBlank() && b2.text.isNotBlank() && c3.text.isNotBlank())
                isSecondDiagonalSame =
                    c1.text.toString() == b2.text.toString() && b2.text.toString() == a3.text.toString()

            if (isFirstRowSame || isSecondRowSame || isThirdRowSame || firstColumnSame || secondColumnSame || thirdColumnSame || isFirstDiagonalSame || isSecondDiagonalSame) {
                showResult()
            } else if(counter == 9) {
                showDrawAlert()
            }
        }

    }

    private fun showDrawAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Draw..!")
        builder.setMessage("Match draw")
        builder.setCancelable(false)
        builder.setPositiveButton("Continue") { dialog, which ->
            resetGameUI()
        }
        builder.show()
    }

    private fun showResult() {
        val winner = if (firstTurn == SelectionType.CROSS) {
            "Nought"
        } else {
            "Cross"
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hurray..!")
        builder.setMessage("$winner won the game")
        builder.setCancelable(false)
        builder.setPositiveButton("Continue") { dialog, which ->
            resetGameUI()
        }
        builder.show()
    }

    private fun resetGameUI() {
        counter = 0
        binding.apply {
            //reset all button text
            recursiveLoopChildren(constraintLayout)
            textView.text = ""
        }
    }

    private fun recursiveLoopChildren(parent: ViewGroup) {
        binding.apply {
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                if (child is ViewGroup) {
                    recursiveLoopChildren(child)
                } else {
                    if (child != null) {
                        (child as? Button)?.text = ""
                    }
                }
            }
        }
    }
}

enum class SelectionType {
    NOUGHT, CROSS
}

