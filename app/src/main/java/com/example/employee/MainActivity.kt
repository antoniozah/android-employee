package com.example.employee

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.employee.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var spinner: Spinner? = null
    private var employeeName: EditText? = null
    private var employeeSurname: TextInputEditText? = null
    private var employeeRole: EditText? = null
    private var companyPart: EditText? = null
    private var selectedTypeOfLeave: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leaveArray = resources.getStringArray(R.array.typeOfLeave)


        spinner = binding.spinner
        employeeName = binding.inputNameEdit
        employeeSurname = binding.inputSurnameEdit
        employeeRole = binding.inputRoleEdit
        companyPart = binding.inputPartEdit

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpf = DatePickerDialog(this, {
                _, mYear, mMonth, dayOfMonth ->
            Toast.makeText(this, "$dayOfMonth/$mMonth/$mYear", Toast.LENGTH_LONG).show()
        }, year, month, day)

        var givenEmployeeName : String = employeeName?.text.toString()
        var givenEmployeeSurname: String = employeeSurname?.text.toString()
        var givenEmployeeRole: String = employeeRole?.text.toString()
        var givenCompanyPart: String = companyPart?.text.toString()

        onSpinnerSelectHandler(leaveArray)
    }

    private fun onSpinnerSelectHandler(leaveArray: Array<String>) {
        if (spinner !== null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, leaveArray)
            spinner?.adapter = adapter

            spinner?.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?, position: Int, id: Long
                ) {
                    if(position > 0) {
                        Toast.makeText(
                            this@MainActivity,
                            getString(R.string.typeOfLeave) + " " +
                                    "" + leaveArray[position], Toast.LENGTH_SHORT
                        ).show()
                        selectedTypeOfLeave = leaveArray[position].toString()
                        Log.i("selectedDrop", selectedTypeOfLeave!!)
                    } else {
                        Toast.makeText(this@MainActivity, "Επίλεξε τύπο αδείας!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    if(selectedTypeOfLeave == "Επιλέξτε τύπο άδειας") {
                        selectedTypeOfLeave = null
                    }
                }
            }
        }
    }


}

