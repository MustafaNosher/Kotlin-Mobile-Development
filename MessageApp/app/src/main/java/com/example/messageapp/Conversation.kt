package com.example.messageapp

data class Conversation(var usernum:Int, var name:String="",var reply:String=" ")


interface ConversationDAO{
    fun insertConversation(std: Conversation)
     fun readConversation(): MutableList<Conversation>
}