package com.bn.flights.ui.base

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Job

abstract class BaseActivity : AppCompatActivity() {
    protected var job: Job? = null
}