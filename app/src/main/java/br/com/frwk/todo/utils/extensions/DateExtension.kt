package br.com.frwk.todo.utils.extensions

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.format(format: String? = "dd/MM/yyyy HH:mm"):String{
    try {
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(this)
    } catch (e: Exception){
        e.printStackTrace()
        return this.toString()
    }
}