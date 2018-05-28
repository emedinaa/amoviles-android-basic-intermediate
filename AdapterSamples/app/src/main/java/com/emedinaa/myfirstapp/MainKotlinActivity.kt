package com.emedinaa.myfirstapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.emedinaa.myfirstapp.R
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainKotlinActivity : AppCompatActivity() {

    companion object {
        val USER_MESSAGE:String="This is my First Android App"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)

        textView.setOnClickListener({
            showMessage(USER_MESSAGE)
        })
    }

    fun showMessage(message:String){
        Log.v("CONSOLE",message)
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }
}
