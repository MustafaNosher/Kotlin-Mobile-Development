package com.example.messageapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConversationAdapter(private var sendercell:String?,private val conversationList:MutableList<Conversation> ) :RecyclerView.Adapter<ConversationAdapter.ViewHolder2>(){

    private lateinit var rListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        rListener = listener
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val itemView = LayoutInflater.from(parent.context)
        val replyList= itemView.inflate(R.layout.conversation_layout, parent, false)

        return ViewHolder2(replyList,rListener)
    }

    override fun onBindViewHolder(holder: ConversationAdapter.ViewHolder2, position: Int) {

        val contact = conversationList[position]
        holder.customBind(sendercell,contact)

    }

    override fun getItemCount(): Int {
        return conversationList.size
    }
    class ViewHolder2(private val itemView: View,listener:OnItemClickListener):RecyclerView.ViewHolder(itemView){

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun customBind(cell:String?,conobject:Conversation){

            val name: TextView = itemView.findViewById(R.id.contactName)

            if(conobject.senderphone==cell) {

                name.text = conobject.reply
            }
            else{
                name.text = conobject.name
            }

        }


    }
}