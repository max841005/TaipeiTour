package com.cst.taipeitour.view

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message: String) = Snackbar.make(this, message,
    BaseTransientBottomBar.LENGTH_SHORT
).apply { setAction("OK") { dismiss() } }.show()