package com.bn.flights.ktx

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.showToast(messageStringId: Int, duration: Int = Toast.LENGTH_SHORT) =
    showToast(getString(messageStringId), duration)

fun AppCompatActivity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Fragment.showToast(messageStringId: Int, duration: Int = Toast.LENGTH_SHORT) =
    showToast(getString(messageStringId), duration)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireActivity(), message, duration).show()