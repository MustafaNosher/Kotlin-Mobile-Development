package com.example.messageapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class MyAdapter(private val messages:List <ChatContent>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {




class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    var message1:TextView= itemView.findViewById(R.id.textView2)
    var message2:TextView=itemView.findViewById(R.id.textView22)
    var message3:TextView=itemView.findViewById(R.id.textView3)
    var message4:TextView=itemView.findViewById(R.id.textView4)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val default_message="Hello! There How may I assist you Today"

      val data=messages[position]
        holder.message1.text=data.message //.message(that is the object of the ChatContent)

        val data2=default_message
        holder.message2.text=data2 //.message(that is the object of the ChatContent)

        //Using Built in Method to print time accroding to cuurent system time
        val currentTime = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val time = dateFormat.format(Date(currentTime))

        holder.message3.text=time.toString()

        holder.message4.text=time.toString()
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}