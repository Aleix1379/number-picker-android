package com.aleixmp.numberpicker

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView

class NumberPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var btnMinus: Button
    private var txtValue: TextView
    private var btnAdd: Button

    private var mMinValue: Int = 0
    private var mMaxValue: Int = 1000
    private var mIncrementer: Int = 1

    private var btnDefaultTextColor = resources.getColor(R.color.secondaryDarkColor)
    private var btnDefaultBackgroundColor = resources.getColor(android.R.color.transparent)

    private var defaultBackground = resources.getDrawable(R.drawable.rectangle)

    private var defaultTextColor = resources.getColor(R.color.md_black_1000)

    init {
        LayoutInflater.from(context).inflate(R.layout.number_picker, this, true)

        txtValue = findViewById(R.id.text_option_value)
        btnMinus = findViewById(R.id.button_option_value_minus)
        btnAdd = findViewById(R.id.button_option_value_add)

        btnMinus.setOnClickListener(this)
        btnAdd.setOnClickListener(this)

        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.NumberPicker, 0, 0
        )

        a.getInteger(R.styleable.NumberPicker_min_value, mMinValue).let {
            if (getValue() < it) {
                setValue(it)
            }
            mMinValue = it
        }

        a.getInteger(R.styleable.NumberPicker_max_value, mMaxValue).let {
            if (getValue() > it) {
                setValue(it)
            }
            mMaxValue = it
        }

        mIncrementer = a.getInteger(R.styleable.NumberPicker_incrementer, mIncrementer)

        btnMinus.setTextColor(a.getColor(R.styleable.NumberPicker_btn_minus_text_color, btnDefaultTextColor))
        btnAdd.setTextColor(a.getColor(R.styleable.NumberPicker_btn_add_text_color, btnDefaultTextColor))

        btnMinus.setBackgroundColor(
            a.getColor(
                R.styleable.NumberPicker_btn_minus_background_color,
                btnDefaultBackgroundColor
            )
        )
        btnAdd.setBackgroundColor(
            a.getColor(
                R.styleable.NumberPicker_btn_add_background_color,
                btnDefaultBackgroundColor
            )
        )

        val background = a.getDrawable(R.styleable.NumberPicker_background)
        if (background != null) {
            this.setBackgroundDrawable(background)
        } else {
            this.setBackgroundDrawable(defaultBackground)
        }

        txtValue.setTextColor(a.getColor(R.styleable.NumberPicker_text_color, defaultTextColor))

        a.recycle()
    }


    override fun onClick(v: View?) {
        v?.let {
            if (it.tag == "minus") {
                minus()
            } else if (it.tag == "add") {
                add()
            }
        }
    }

    fun minus() {
        setValue(getValue() - mIncrementer)
    }

    fun minus(value: Int) {
        setValue(getValue() - value)
    }

    fun add() {
        setValue(getValue() + mIncrementer)
    }

    fun add(value: Int) {
        setValue(getValue() + value)
    }

    fun setValue(newValue: Int) {
        when (newValue) {
            in mMinValue..mMaxValue -> txtValue.text = newValue.toString()
        }
    }

    fun getValue() = txtValue.text.toString().toInt()
}
