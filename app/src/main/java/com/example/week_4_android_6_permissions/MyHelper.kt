package com.example.week_4_android_6_permissions

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context

class MyHelper(ctx:Context) : SQLiteOpenHelper(ctx,"TestDB", null, 1) {


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL ("CREATE TABLE IF NOT EXISTS People (Id INTEGER PRIMARY KEY, Title VARCHAR(255), Artist VARCHAR(255), Year INTEGER)")
    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL ("DROP TABLE IF EXISTS People")
        onCreate(db)
    }

    fun insert(Title: String, Aritst: String, Year: Long) : Long? {
        val db = getWritableDatabase()
        val stmt = db.compileStatement ("INSERT INTO people(Title,Artist,Year) VALUES (?, ?, ?)");
        stmt.bindString (1, Title)
        stmt.bindString (2, Aritst )
        stmt.bindLong (3, Year)
        val id = stmt.executeInsert()
        return id
    }
    fun search(Id: Long): Long? {
        val db = getReadableDatabase()
        val cursor = db.rawQuery("SELECT * FROM People WHERE Id=?", arrayOf<String>(Id.toString()) )

            if (cursor.moveToFirst()) {
                val p = Song ( //TODO CREATE A SONG OBJECT! A CLASS THAT HAVE -> data class Point(val x:Int, val y: Int) <- SEE WEEK 1 DATA CLASS!
                    cursor.getString(cursor.getColumnIndex("Title")),
                    cursor.getString(cursor.getColumnIndex("Artist")),
                    cursor.getLong(cursor.getColumnIndex("Year"))
                )
                cursor.close()
                return p
            }
                cursor.close()
                return null
            }
        }
}



