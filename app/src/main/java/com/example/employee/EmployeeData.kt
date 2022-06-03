package com.example.employee

import java.io.Serializable

data class EmployeeData(
    val fname : String,
    val lname: String,
    val role: String,
    val companyPart: String,
    val typeOfLeave: String,
    val dateFrom: String,
    val dateTo: String,
    val leaveDays: String?,
    val reason: String?,
    val withSalaries: String?,
    val basicLeave: String?,
    val ikaLeave: String?,
    val withoutSalaries: String?
) : Serializable
