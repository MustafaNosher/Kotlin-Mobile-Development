package com.example.messageapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class MyAdapter( private var messages: MutableList<Message> = mutableListOf<Message>()): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    companion object {
        private const val TOP_MESSAGE = 1
        private const val BOTTOM_MESSAGE = 2
    }


    override fun getItemViewType(position: Int): Int{
        val messageObj=messages[position]
        if(messageObj.status=="SEND"){
            return TOP_MESSAGE
        }
        else{
            return BOTTOM_MESSAGE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.message_layout, parent, false)
        val viewObj:View
        if(viewType== TOP_MESSAGE){

            viewObj= itemView.inflate(R.layout.message_layout, parent, false)

        }else{

            viewObj= itemView.inflate(R.layout.botmessage_layout, parent, false)

        }

        return ViewHolder(viewObj)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data=messages[position]

        if(data.status=="SEND"){

            holder.bintoTop(data)

        }
        else{
           holder.bindtoBottom(data)

        }


    }

    override fun getItemCount(): Int {
        return messages.size
    }
    class ViewHolder(private var itemView: View): RecyclerView.ViewHolder(itemView){



         //Binds the data to the view holder related to the user/Sender
        fun bintoTop(messageObj:Message){

            var message1:TextView= itemView.findViewById(R.id.textView2) //sender message
            var message3:TextView=itemView.findViewById(R.id.textView3) // SenderTIMESTAMP
            var message5:TextView=itemView.findViewById(R.id.textView) //Name of Sender

            message1.text=messageObj.message_body.toString()
            message3.text=messageObj.time_stamp.toString()
            message5.text=messageObj.name.toString()



        }
        //Bind the view holder related to auto generated reply
        fun bindtoBottom(messageObj:Message){

            var message2:TextView=itemView.findViewById(R.id.textView22) // reply message
            var message4:TextView=itemView.findViewById(R.id.textView4) // Reply TIMESTAMP
            var message6:TextView=itemView.findViewById(R.id.textView11) //Name of reply person

            message2.text=messageObj.message_body.toString()
            message4.text=messageObj.time_stamp.toString()
            message6.text=messageObj.name.toString()


        }
    }
}