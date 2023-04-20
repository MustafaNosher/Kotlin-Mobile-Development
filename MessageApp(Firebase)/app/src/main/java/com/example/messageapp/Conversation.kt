package com.example.messageapp

data class Conversation(var usernum:Int?=null, var name:String?=null,var reply:String?="")




interface ConversationDAO {
    fun insertConversation(std: Conversation)
    fun readConversation(): MutableList<Conversation>
}

