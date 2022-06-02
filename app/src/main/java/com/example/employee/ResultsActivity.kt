package com.example.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.employee.databinding.ActivityMainBinding
import com.example.employee.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {

    // ╔═══════════════════════════════════════════════════════════════════════════════════════════╗
    // ║ VARIABLES                                                                                 ║
    // ╚═══════════════════════════════════════════════════════════════════════════════════════════╝
    // ┌───────────────────────────────────────────────────────────────────────────────────────────┐
    //   → PRIVATE VARIABLES
    // └───────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * Layout binding.
     */
    private lateinit var binding: ActivityResultsBinding

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
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val employeeInfo = intent.getSerializableExtra("EMPLOYEE_DATA")

        binding.tvEmployeeData.text = employeeInfo.toString()
    }
}