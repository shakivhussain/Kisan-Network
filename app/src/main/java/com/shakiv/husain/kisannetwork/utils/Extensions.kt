package com.shakiv.husain.kisannetwork.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


inline fun <reified T> T?.toStringOrEmpty(): String {
    return this?.toString() ?: ""
}

fun Fragment.navigate(actionId: Int, bundle: Bundle?, navOption: NavOptions?) {
    findNavController().navigate(actionId, bundle, navOption)
}

fun Fragment.navigateToDestination(bundle: Bundle? = null, actionId: Int) {
    val navOption = NavOptions.Builder()
        .build()
    navigate(actionId, bundle, navOption)
}

fun logd(log: String, tag: String = "TAG") {
    Log.d(tag, "Log : $log ")
}

fun loge(log: String, tag: String = "TAG") {
    Log.e(tag, "Log : $log ")
}

fun Fragment.toast(activity: Context, message:String){
    Toast.makeText(activity, "$message", Toast.LENGTH_SHORT).show()
}


fun Long.toFormattedDateTime(): String {
    val sdf = SimpleDateFormat("hh:mm:ss a \n dd/MM/yyyy", Locale.getDefault())
    val date = Date(this)
    return sdf.format(date)
}

fun RecyclerView.itemAnimationDisable() {
    (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
}
