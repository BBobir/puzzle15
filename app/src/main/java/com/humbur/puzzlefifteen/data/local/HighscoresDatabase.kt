package com.humbur.puzzlefifteen.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class HighscoresDatabase(context: Context) : SQLiteOpenHelper(
    context, DatabaseConfig.DATABASE_NAME, null, DatabaseConfig.VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE ${DatabaseConfig.TABLE_NAME}(${DatabaseConfig.COL_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${DatabaseConfig.COL_MOVES} INTEGER)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${DatabaseConfig.TABLE_NAME} ")
    }

    fun insert(highscore: Highscore) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        Log.d("NEW_TAG", "insert: ${highscore.moves}")
       // contentValues.put(DatabaseConfig.COL_ID, highscore.id)
        contentValues.put(DatabaseConfig.COL_MOVES, highscore.moves)
        db.insert(DatabaseConfig.TABLE_NAME, null, contentValues)
      //  db.close()
    }

    fun getHighscores():List<Highscore>{
        val list :MutableList<Highscore> = ArrayList()
        val db = this.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM ${DatabaseConfig.TABLE_NAME} ORDER BY ${DatabaseConfig.COL_MOVES} ASC LIMIT 5",null)
        if (cursor.moveToNext()){
            do{
                val id = cursor.getInt(0)
                val moves = cursor.getInt(1)
                val highscore = Highscore(id,moves)
                list.add(highscore)
            }while (cursor.moveToNext())
        }
//        db.close()
        return list
    }
    fun clearAllHighscores(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${DatabaseConfig.TABLE_NAME}")
//        db.close()
    }
}
