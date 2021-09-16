package com.example.ageinminutes

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { view ->
            clickbtnlistner(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickbtnlistner(view: View){
        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)

        val dapd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, year, month, DayOfMonth ->

            val selectedDate = "$DayOfMonth/${month+1}/$year "

            showSelectedDate.setText(selectedDate).toString()

            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

            val thDate = sdf.parse(selectedDate)
            val datemin = thDate!!.time /60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currmin = currentDate!!.time/60000

            val finalMin = currmin - datemin

            showMinute.setText(finalMin.toString())

        }
            ,year
            , month
            , day)
        dapd.datePicker.setMaxDate(Date().time - 86400000)

        dapd.show()

    }
}