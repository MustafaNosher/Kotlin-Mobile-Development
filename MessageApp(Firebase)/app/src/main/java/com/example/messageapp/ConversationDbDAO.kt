//package com.example.messageapp
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.widget.Toast
//
//class ConversationDbDAO:ConversationDAO {
//
//    var context: Context
//
//
//    lateinit var dbHelper:DataBaseHandler
//
//    constructor(ctx: Context){
//        context=ctx
//        dbHelper = DataBaseHandler(context)
//    }
//
//    override fun insertConversation(std: Conversation) {
//
//
//        lateinit var db: SQLiteDatabase
//
//
//        db=dbHelper.writableDatabase
//
//        val contentValues = ContentValues()
//        contentValues.put(DataBaseHandler.ID,std.usernum)
//        contentValues.put(DataBaseHandler.USER, std.name)
//        contentValues.put(DataBaseHandler.BOT, std.reply)
//        val status = db.insert(DataBaseHandler.TABLE_NAME, null, contentValues)
//        if(status==-1.toLong()){
//
//
//            Toast.makeText(context,"Record Not Saved in Conversation Table", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context,"Record  Saved in Conversation Table", Toast.LENGTH_SHORT).show()
//        }
//
//
//    }
//    override fun readConversation(): MutableList<Conversation> {
//        val conversationList = mutableListOf<Conversation>()
//        lateinit var db:SQLiteDatabase
//
////        val db = this.readableDatabase
//        db=dbHelper.readableDatabase
//
//        val sql = "Select * from ${DataBaseHandler.TABLE_NAME}"
//        val fetch = db.rawQuery(sql, null)
//        while (fetch.moveToNext()) {
//            val id=fetch.getString(0).toInt()
//            val sendername=fetch.getString(1)
//            val recievername=fetch.getString(2)
//
//            conversationList.add(Conversation(id,sendername,recievername))
//
//        }
//        fetch.close()
//        db.close()
//
//        return conversationList
//    }
//}