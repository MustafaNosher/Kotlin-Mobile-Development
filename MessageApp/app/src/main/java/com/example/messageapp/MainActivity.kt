package com.example.messageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {




    private var messageList = mutableListOf<ChatContent>()

    private lateinit var rv:RecyclerView //recycler view type object
    private lateinit var customadapter:MyAdapter //adapter type object
    private lateinit var textInput: EditText // Edit Text type object
    private lateinit var sendButton: Button // Button Type Object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sendButton=findViewById(R.id.button) // id of send button that user will click to send the message


        rv=findViewById(R.id.recyclerView)
        rv.layoutManager=LinearLayoutManager(this)

       sendButton.setOnClickListener{
           sendChat()
       }
    }
    private fun sendChat(){
        textInput=findViewById(R.id.messagebox) //id of view where the user will writes its message
        val messageText = textInput.text.toString().trim()
        val default_message="Hello There How May I assist You!"
        if (messageText.isNotEmpty()){

            customadapter= MyAdapter(messageList)
            rv.adapter=customadapter
            rv.addItemDecoration( DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
            val data=ChatContent(messageText,default_message)
            messageList+=data // this part save the CONVERSATION in the global List
            customadapter.notifyDataSetChanged()
            // Scroll the RecyclerView to the end so that the new message is visible
            rv.scrollToPosition(messageList.size - 1)
            // Clear the EditText field so that the user can enter a new message
            textInput.text.clear()
        }

    }
}
