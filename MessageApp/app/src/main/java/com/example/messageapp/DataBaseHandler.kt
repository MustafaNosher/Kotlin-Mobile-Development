package com.example.messageapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import android.widget.Toast






//Define the messages table attributes
//const val TABLE_NAME2="Messages"
//const val COL_ID2="Message_ID"
//const val MESSAGE_TEXT="LAST_MESSAGE"
//const val TIME_STAMP="TIME STAMP"
//const val STATUS="CURRENT_STATUS"
//const val ID_CONVERSATION="CONVERSATION_ID"
//const val SENDER="SENDER_USERNAME"
//const val RECEIVER="RECEIVER_USERNAME"

//object Concontract{
//    object Entry:BaseColumns{
//        const val TABLE_NAME="Conversation"
//        const val USER="USER_NAME"
//        const val TIMESTAMP="TIME"
//    }
//}
//const val CREATE_CONVERSATION_TABLE="CREATE TABLE ${Concontract.Entry.TABLE_NAME}("+
//        "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
//        "${Concontract.Entry.USER} TEXT,"+
//        "${Concontract.Entry.TIMESTAMP} INTEGER)"
//
//const val DELETE_CONVERSATION_TABLE =
//    "DROP TABLE IF EXISTS ${Concontract.Entry.TABLE_NAME}"


class DataBaseHandler( var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME="message.db"
        private const val TABLE_NAME="conversation"
        private const val USER="USER_NAME"
        private const val BOT="Reply_Name"
        private const val ID="id"

    }





    override fun onCreate(p0: SQLiteDatabase?) {

     val CREATE_CONVERSATION_TABLE=( "CREATE TABLE "+ TABLE_NAME+ "(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER + " TEXT," + BOT + " TEXT" +
             "" + ")")

        p0?.execSQL(CREATE_CONVERSATION_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        p0?.execSQL("ALTER TABLE conversation ADD COLUMN Reply_Name TEXT")

      p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }


    fun insertConversation(std:Conversation):Long{


        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(USER,std.name)
        contentValues.put(BOT,std.reply)
        val success=db.insert(TABLE_NAME,null,contentValues)
        db.close()
        return success


    }

//    fun insertConverstation(name:String,timestamp:Long){
//        val db = dbHelper.writableDatabase
//        val values = ContentValues().apply {
//            put(Concontract.Entry.USER, name)
//            put(Concontract.Entry.TIMESTAMP, timestamp)
//        }
//        val newRowId=db?.insert(Concontract.Entry.TABLE_NAME,null,values)
//        if (newRowId==-1.toLong()) {
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        }
//        else{
//                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
//            }
//        db.close()
//        }

    }

