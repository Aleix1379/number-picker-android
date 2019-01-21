package com.aleixmp.numberpicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)

        val numberPicker: NumberPicker = findViewById(R.id.number_picker_items)

        numberPicker.setValue(28)

        btn1.setOnClickListener {
            numberPicker.minus(10)
        }

        btn2.setOnClickListener {
            numberPicker.add(10)
        }

        btn3.setOnClickListener {
            numberPicker.setValue(50)
        }

    }
}
