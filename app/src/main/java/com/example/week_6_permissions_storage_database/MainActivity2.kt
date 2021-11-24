package com.example.week_6_permissions_storage_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        val saveBtn = findViewById<Button>(R.id.save)
        val inputName = findViewById<EditText>(R.id.name)
        val inputAge = findViewById<EditText>(R.id.age)
        val inputAdult = findViewById<RadioButton>(R.id.isAdult)
        saveBtn.setOnClickListener {
            val name = inputName.text.toString()
            val age = inputAge.text.toString().toInt()
            val adult = inputAdult.isChecked

            editor.apply {
                putString("name", name)
                putInt("age", age)
                putBoolean("isAdult", adult)
                apply()
            }

        }
        val loadBtn = findViewById<Button>(R.id.load)
        loadBtn.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val age = sharedPref.getInt("age", 100)
            val adult = sharedPref.getBoolean("isAdult", false)

            inputName.setText(name)
            inputAge.setText(age.toString())
            inputAdult.isChecked = adult

        }
    }
}