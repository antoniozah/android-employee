package com.example.employee

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.employee.databinding.ActivityMainBinding
import com.google.android.material.textview.MaterialTextView
import java.util.*

class MainActivity : AppCompatActivity()  {

    // ╔═══════════════════════════════════════════════════════════════════════════════════════════╗
    // ║ VARIABLES                                                                                 ║
    // ╚═══════════════════════════════════════════════════════════════════════════════════════════╝
    // ┌───────────────────────────────────────────────────────────────────────────────────────────┐
    //   → PRIVATE VARIABLES
    // └───────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * Layout binding.
     */
    private lateinit var binding: ActivityMainBinding

    private var selectedTypeOfLeave : String? = null

    // ╔═══════════════════════════════════════════════════════════════════════════════════════════╗
    // ║ FUNCTIONS                                                                                 ║
    // ╚═══════════════════════════════════════════════════════════════════════════════════════════╝
    // ┌───────────────────────────────────────────────────────────────────────────────────────────┐
    //   → OVERRIDE FUNCTIONS
    // └───────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling [setContentView] to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * [managedQuery] to retrieve
     * cursors for data being displayed, etc.
     *
     * You can call [finish] from within this function, in
     * which case [onDestroy] will be immediately called after [onCreate] without any of the
     * rest of the activity lifecycle ([onStart], [onResume], [onPause] etc)
     * executing.
     *
     * Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in onSaveInstanceState.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leaveArray = resources.getStringArray(R.array.typeOfLeave)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.dateFromBtn.setOnClickListener {
            onDatePickerHandler(year, month, day, binding.dateFromDate)
        }

        binding.dateToBtn.setOnClickListener {
            onDatePickerHandler(year, month, day, binding.dateToDate)
        }

        onSpinnerSelectHandler(leaveArray)

        binding.submitBtn.setOnClickListener {
            val fname = binding.inputNameEdit.text.toString()
            val lname = binding.inputSurnameEdit.text.toString()
            val role = binding.inputRoleEdit.text.toString()
            val part = binding.inputPartEdit.text.toString()
            val dateFrom = binding.dateFromDate.text.toString()
            val dateTo = binding.dateToDate.text.toString()
            val leaveDays = binding.inputDaysEdit.text?.toString() ?: "-"
            val reason = binding.inputReasonEdit.text.toString()
            val withSalaries = binding.inputSalariesEdit.text?.toString() ?: "-"
            val basicLeave = binding.inputBasicLeaveEdit.text?.toString() ?: "-"
            val ikaLeave = binding.inputIkaLeaveEdit.text?.toString() ?: "-"
            val withoutSalaries = binding.inputNoSalariesEdit.text?.toString() ?: "-"

            val employeeInfo = EmployeeData(fname, lname, role, part, selectedTypeOfLeave.toString(), dateFrom, dateTo, leaveDays, reason, withSalaries, basicLeave, ikaLeave, withoutSalaries )
            Intent(this, ResultsActivity::class.java).also {
                it.putExtra("EMPLOYEE_DATA", employeeInfo)
                startActivity(it)
            }
        }
    }

    private fun inputFieldsVisibilityHandler(matchArgument: String) {
        when (matchArgument) {
            "Άδεια μετά αποδοχών" -> {
                binding.inputReasonLayout.visibility = View.VISIBLE
                binding.inputSalariesLayout.visibility = View.GONE
                binding.inputBasicLeaveLayout.visibility = View.GONE
                binding.inputNoSalariesLayout.visibility = View.GONE
                binding.inputIkaLeaveLayout.visibility = View.GONE
            }

            "Άδεια ασθενείας" -> {
                binding.inputReasonLayout.visibility = View.GONE
                binding.inputSalariesLayout.visibility = View.VISIBLE
                binding.inputBasicLeaveLayout.visibility = View.VISIBLE
                binding.inputNoSalariesLayout.visibility = View.VISIBLE
                binding.inputIkaLeaveLayout.visibility = View.VISIBLE
            }
            else -> {
                binding.inputReasonLayout.visibility = View.GONE
                binding.inputSalariesLayout.visibility = View.GONE
                binding.inputBasicLeaveLayout.visibility = View.GONE
                binding.inputNoSalariesLayout.visibility = View.GONE
                binding.inputIkaLeaveLayout.visibility = View.GONE
            }
        }
    }

    private fun onDatePickerHandler(year: Int, month: Int,day: Int, selectedDateText: MaterialTextView) {
        val dpf = DatePickerDialog(this, {
                _, mYear, mMonth, dayOfMonth ->
            val selDate = "$dayOfMonth/$mMonth/$mYear"
            Toast.makeText(this, selDate, Toast.LENGTH_LONG).show()
            selectedDateText.text = selDate
        }, year, month, day)
        dpf.show()
    }

    private fun onSpinnerSelectHandler(leaveArray: Array<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, leaveArray)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (position > 0) {
                        Toast.makeText(
                        this@MainActivity,
                        getString(R.string.typeOfLeave) + " " +
                                "" + leaveArray[position], Toast.LENGTH_SHORT
                        ).show()
                        selectedTypeOfLeave = leaveArray[position]
                        selectedTypeOfLeave?.let { inputFieldsVisibilityHandler(it) }
                    } else {
                        Toast.makeText(this@MainActivity, "Επίλεξε τύπο αδείας!", Toast.LENGTH_SHORT).show()
                        selectedTypeOfLeave = leaveArray[0]
                        selectedTypeOfLeave?.let { inputFieldsVisibilityHandler(it) }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
    }
}

