package com.example.week_4_android_6_permissions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var helper: MyHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helper = MyHelper (this)



        Add.setOnClickListener { Toast.makeText(this, "You clicked me ", Toast.LENGTH_SHORT).show() }
        Search.setOnClickListener { Toast.makeText(this, "You clicked me!", Toast.LENGTH_SHORT).show()}
        }
}
