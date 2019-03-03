package com.example.Week_3_Databases_SQLite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var helper: MyHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar1)

        helper = MyHelper(ctx = this)
        Add.setOnClickListener {
            val title = BTitle.text.toString()
            val artist = BArtist.text.toString()
            val year = BYear.text.toString().toLong()
            val id = helper.insert(title, artist, year)
            Toast.makeText( this,"You successfully added this song", Toast.LENGTH_LONG).show()
        }
        Search.setOnClickListener {
            val id = BId.text.toString().toLong()
            val database = helper.search(id)
            BTitle.setText(database?.title)
            BArtist.setText(database?.artist)
            BYear.setText(database?.year.toString())
            Toast.makeText( this,"You successfully searched for this song", Toast.LENGTH_LONG).show()
        }
        Uptade.setOnClickListener {
            val id = BId.text.toString().toLong()
            val title = BTitle.text.toString()
            val artist = BArtist.text.toString()
            val year = BYear.text.toString().toLong()
            val uptade = helper.uptade(id, title, artist, year)
        }
        Delete.setOnClickListener {
            val id = BId.text.toString().toLong()
            val delete = helper.delete(id)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
