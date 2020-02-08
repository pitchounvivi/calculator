package com.example.calculateur

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

fun showToast(ctxt: Context, msg: String){ //les variables sont directements déclarée dans les paramètres

    Toast.makeText(ctxt, msg, Toast.LENGTH_LONG).show()

}


fun showSuperToast(act: Activity, msg: String){
    var inflater: LayoutInflater = act.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var view: View = inflater.inflate(R.layout.toast, act.findViewById(R.id.llToast))
    var tvToast: TextView = view.findViewById<TextView>(R.id.tvToast)
    tvToast.setText(msg)

    var toast: Toast = Toast.makeText(act, msg, Toast.LENGTH_LONG)
    toast.view = view
    toast.show()
}