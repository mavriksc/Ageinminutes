package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val tvSelectedDate by lazy { findViewById<TextView>(R.id.tvSelectedDate) }
    private val tvTimeInMinutes by lazy { findViewById<TextView>(R.id.tvTimeInMinutes) }
    private val buttonDatePicker by lazy { findViewById<Button>(R.id.buttonDatePicker) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonDatePicker.setOnClickListener { cluckDatePucker() }
    }

    private fun cluckDatePucker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, { _, y, m, d ->
            tvSelectedDate.text = "$d/${m + 1}/$y"
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val selectedDate = simpleDateFormat.parse(tvSelectedDate.text as String)
            val dif = Date().time - selectedDate!!.time
            tvTimeInMinutes.text =
                DecimalFormat.getInstance().format(TimeUnit.MILLISECONDS.toMinutes(dif))
        }, year, month, day).show()
    }
}