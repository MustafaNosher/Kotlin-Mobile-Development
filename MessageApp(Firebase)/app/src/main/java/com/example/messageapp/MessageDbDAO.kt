//package com.example.messageapp
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import kotlin.coroutines.coroutineContext
//
//class MessageDbDAO:MessageDao{
//
//     var context: Context
//    lateinit var dbHelper:DataBaseHandler
//
//  constructor(ctx:Context){
//        context=ctx
//        dbHelper = DataBaseHandler(context)
//    }
//    override fun insertMessage(msg:Message){
//
//
//
//
//        lateinit var db:SQLiteDatabase
//
//
//        db=dbHelper.writableDatabase
//
////        val db=context.writableDatabase
//        val contentValues = ContentValues()
//
//        contentValues.put("${DataBaseHandler.CONVERSATION}",msg.conversation_id)
//        contentValues.put("${DataBaseHandler.NAME}",msg.name)
//        contentValues.put("${DataBaseHandler.body}",msg.message_body)
//        contentValues.put("${DataBaseHandler.TIMESTAMP}",msg.time_stamp)
//        contentValues.put("${DataBaseHandler.STATUS}",msg.status)
//
//        val status=db.insert(DataBaseHandler.TABLE_NAME2,null,contentValues)
//        db.close()
//
//        if(status==-1.toLong()){
//
//
//            Toast.makeText(context,"Record  Not Saved in Message Table", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context,"Record  Saved in Message Table", Toast.LENGTH_SHORT).show()
//        }
//
//    }
//    override fun readmessage(id:Int):MutableList<Message>{
//
//        val messageList = mutableListOf<Message>()
//        lateinit var db:SQLiteDatabase
//
////        val db = this.readableDatabase
//        db=dbHelper.readableDatabase
//
//        val sql = "Select m.${DataBaseHandler.CONVERSATION}, m.${DataBaseHandler.body}, m.${DataBaseHandler.NAME} , m.${DataBaseHandler.TIMESTAMP}, m.${DataBaseHandler.STATUS} from ${DataBaseHandler.TABLE_NAME2} as m join ${DataBaseHandler.TABLE_NAME} as n on n.${DataBaseHandler.ID} = m.${DataBaseHandler.CONVERSATION} Where m.${DataBaseHandler.CONVERSATION} = $id"
//        val fetch = db.rawQuery(sql, null)
//        while(fetch.moveToNext()){
//            val conversationID=fetch.getString(0).toInt()
//            val message=fetch.getString(1)
//            val name=fetch.getString(2)
//            val timestamp=fetch.getString(3)
//            val status=fetch.getString(4)
//
//
//            messageList.add(Message(conversationID,name,message,timestamp,status))
//
//        }
//        fetch.close()
//        return messageList
//
//    }
//
//
//}