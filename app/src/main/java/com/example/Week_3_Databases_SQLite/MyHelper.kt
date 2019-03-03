package com.example.Week_3_Databases_SQLite

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import android.support.v7.widget.DialogTitle
import android.widget.TableRow

data class Song (val id: Long, val title: String, val artist: String, val year: Long)
class MyHelper(ctx:Context) : SQLiteOpenHelper(ctx,"MyDatabase", null, 1) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL ("CREATE TABLE IF NOT EXISTS People (Id INTEGER PRIMARY KEY, Title VARCHAR(255), Artist VARCHAR(255), Year LONG)")
    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL ("DROP TABLE IF EXISTS People")
        onCreate(db)
    }

    fun insert(title: String, artist: String, year: Long) : Long{
        val db = writableDatabase
        val stmt = db.compileStatement ("INSERT INTO People (Title, Artist, Year) VALUES (?, ?, ?)");
        stmt.bindString (1, title)
        stmt.bindString (2, artist)
        stmt.bindLong (3, year)
        val id = stmt.executeInsert()
        return id
    }
    fun search(id: Long) : Song? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM People WHERE Id=?", arrayOf(id.toString()) )
            if (cursor.moveToFirst()) {
                val p = Song ( cursor.getLong(cursor.getColumnIndex("Id")),
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
    fun uptade(id: Long, title: String, artist: String, year: Long) : Int {
        val db = writableDatabase
        val stmt = db.compileStatement ("UPDATE People SET Title=?, Artist=?, Year=? WHERE Id=?");
        stmt.bindString (1, title)
        stmt.bindString (2, artist)
        stmt.bindLong (3, year)
        stmt.bindLong(4, id)
        val nAffectedRows = stmt.executeUpdateDelete();
        return nAffectedRows
    }
    fun delete(id: Long) : Int {
        val db = writableDatabase
        val stmt = db.compileStatement  ("DELETE FROM People WHERE Id=?");
        stmt.bindLong(1, id)
        val nAffectedRows = stmt.executeUpdateDelete();
        return nAffectedRows
    }

}





