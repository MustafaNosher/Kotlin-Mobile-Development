package com.example.messageapp

import androidx.recyclerview.widget.RecyclerView

data class Conversation(var usernum:String?=null, var name:String?=null,var reply:String?="",var senderphone:String?=null){


}




interface ConversationDAO {
    fun insertConversation(std: Conversation)
    fun readConversation(iD:String?): MutableList<Conversation>
//   fun OnDataReceive(obj:Conversation): MutableList<Conversation>

}

