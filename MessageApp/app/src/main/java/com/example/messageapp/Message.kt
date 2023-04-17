package com.example.messageapp

data class Message(var conversation_id: Int, var name: String, var message_body: String, var time_stamp: String,var status:String="DRAFT")


interface MessageDao{

    fun insertMessage(msg:Message)
    fun readmessage(id:Int):MutableList<Message>
}
