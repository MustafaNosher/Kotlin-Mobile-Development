package com.example.messageapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DataBaseHandler( var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "message.db"

        //Columns for the Conversation Table
        const val TABLE_NAME = "conversation"
        const val USER = "USER_NAME"
        const val BOT = "Reply_Name"
       const val ID = "id"

        //Columns for the Messages Table

        const val TABLE_NAME2 = "messages"
        const val ID2 = "Message_ID"
        const val body = "LAST_MESSAGE"
        const val TIMESTAMP = "TIME_STAMP"
        const val STATUS = "CURRENT_STATUS"
        const val CONVERSATION = "CONVERSATION_ID"
        const val NAME="USERNAME"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val CREATE_CONVERSATION_TABLE =
            ("CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER + " TEXT," + BOT + " TEXT" +
                    "" + ")")

        p0?.execSQL(CREATE_CONVERSATION_TABLE)

        val CREATE_MESSAGE_TABLE =
            ("CREATE TABLE " + TABLE_NAME2 +  "(" + ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONVERSATION + " INTEGER, " + body + " TEXT, " + TIMESTAMP + " TEXT, " + STATUS + " TEXT," + NAME + " TEXT " +
                   "" + ")" )

        p0?.execSQL(CREATE_MESSAGE_TABLE)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

//        p0?.execSQL("ALTER TABLE conversation ADD COLUMN Reply_Name TEXT")

        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME2")
        onCreate(p0)
    }

}


