package com.redrock.halloffame.utils

import android.widget.Toast
import com.redrock.halloffame.base.BaseApplication

fun toast(s: String) {
    Toast.makeText(BaseApplication.appContext, s, Toast.LENGTH_SHORT).show()
}

fun toastLong(s: String) {
    Toast.makeText(BaseApplication.appContext, s, Toast.LENGTH_LONG).show()
}