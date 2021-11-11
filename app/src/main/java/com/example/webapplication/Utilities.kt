package com.example.webapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

//
//fun Spinner.selected(action: () -> Unit) {
//    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//        override fun onNothingSelected(parent: AdapterView<*>?) {}
//        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            action()
//        }
//    }
//}

fun toast(con: Context, s: String) {
    Toast.makeText(con, s, LENGTH_LONG).show()
}

// When User cilcks on dialog button, call this method
fun onAlertDialog(
    view: View,
    title: String, message: String,
    pButton: String="update now", nButton: String="cancel",
    pListener: DialogInterface.OnClickListener, nListener: DialogInterface.OnClickListener
) {

    //Instantiate builder variable
    val builder = AlertDialog.Builder(view.context)

    // set title
    builder.setTitle(title)

    //set content area
    builder.setMessage(message)

    //set negative button
    builder.setPositiveButton(
        pButton, pListener
    )
    //set positive button
    builder.setNegativeButton(
        nButton, nListener
    )
    //set neutral button
    builder.setNeutralButton("Reminder me latter") { dialog, id ->
        dialog.dismiss()     // User Click on reminder me latter
    }
    builder.show()
}