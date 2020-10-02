package com.infrontofthenet.f20lesson3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import android.widget.SeekBar.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    // vars for calculation
    var amount = 0.0
    var tipPercent = 0.15
    var tip = 0.0
    var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // when amount is changed
        editTextAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                amount = if (editTextAmount.text.isNotEmpty()) editTextAmount.text.toString().toDouble() else 0.0

                calculate()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // when tip % is changed
        seekBar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (editTextAmount.text.isEmpty()) {
                    return
                }
                textViewTipPercent.setText(p1.toString() + '%')
                tipPercent = (p1 / 100.00)
                calculate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                            }

        })
    }

    fun calculate() {
        // calc & display tip and total amounts
        tip = amount * tipPercent
        total = amount + tip

        val currencyFormat = NumberFormat.getCurrencyInstance()

        // display in the text views
        textViewTipAmount.setText(currencyFormat.format(tip).toString())
        textViewTotalAmount.setText(currencyFormat.format(total).toString())
    }
}