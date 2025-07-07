package com.domain.myapplication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val buttonSelectBirthDate = findViewById<Button>(R.id.buttonSelectBirthDate)
        buttonSelectBirthDate.setOnClickListener {

            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)

            val textViewSelectedDate = findViewById<TextView>(R.id.textViewSelectedDate)
            val textViewResult = findViewById<TextView>(R.id.textViewResult)
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val selectedDateInString =
                        "${selectedDayOfMonth}/${selectedMonth + 1}/${selectedYear}"
                    Toast.makeText(this, selectedDateInString, Toast.LENGTH_LONG).show()
                    val simpleDateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val selectedDateInFormat = simpleDateFormatter.parse(selectedDateInString)
                    val selectedDateInMinutes =
                        selectedDateInFormat?.time?.div(60000) ?: return@OnDateSetListener
                    val currentDate =
                        simpleDateFormatter.parse(simpleDateFormatter.format(System.currentTimeMillis()))

                    val currentDateInMinutes =
                        currentDate?.time?.div(60000) ?: return@OnDateSetListener

                    val birthToMinutes = currentDateInMinutes - selectedDateInMinutes
                    textViewSelectedDate.setText(selectedDateInString)
                    textViewResult.setText(birthToMinutes.toString())

                },
                year,
                month,
                day
            )
            datePicker.datePicker.maxDate = Date().time
            datePicker.show()
        }
    }


}
