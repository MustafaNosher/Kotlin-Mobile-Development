package com.example.messageapp

data class Message(var conversation_id: Int?=null, var name: String?=null, var message_body: String?=null, var time_stamp: String?=null,var status:String?="DRAFT")


interface MessageDao{

    fun insertMessage(msg:Message)
    fun readmessage(id:Int):MutableList<Message>
}
