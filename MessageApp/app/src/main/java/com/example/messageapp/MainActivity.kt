package com.example.messageapp
import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {




//    private var messageList = mutableListOf<ChatContent>()

    private lateinit var rv:RecyclerView //recycler view type object
    private lateinit var customadapter:MyAdapter //adapter type object
    private lateinit var textInput: EditText // Edit Text type object
    private lateinit var sendButton: Button // Button Type Object
    private lateinit var MDAO:  MessageDao //MESSAGE domain layer object







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chatIdentifier=intent.getIntExtra("ID",0)
        val name=intent.getStringExtra("NAME") // Receives the name of the person with whom to began the conversation



//        val db=DataBaseHandler(this)
        MDAO=MessageDbDAO(this)
        val chat=MDAO.readmessage(chatIdentifier)

        rv=findViewById(R.id.recyclerView)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=MyAdapter(chat)




        sendButton=findViewById(R.id.button) // id of send button that user will click to send the message

        sendButton.setOnClickListener{
        textInput=findViewById(R.id.messagebox) //id of view where the user will writes its message
        val messageText = textInput.text.toString().trim()
        val default_message="Hello There How May I assist You!"
        if (messageText.isNotEmpty()){


            val currentTime = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val time = dateFormat.format(Date(currentTime))

            rv.addItemDecoration( DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

            chat.add(Message(chatIdentifier,"MustafaNosher",messageText,time,"SEND"))

            chat.add(Message(chatIdentifier,name!!,default_message,time,"RECEIVE"))





            //rv.notifyDataSetChanged()
            // Scroll the RecyclerView to the end so that the new message is visible
            rv.scrollToPosition(chat.size - 1)

            // Clear the EditText field so that the user can enter a new message
            textInput.text.clear()

            //INSERT THE DATA IN THE DATABASE
//            db.insertMessage(Message(chatIdentifier,"MustafaNosher",messageText,time,"0"))
            MDAO.insertMessage(Message(chatIdentifier,"MustafaNosher",messageText,time,"0"))
            MDAO.insertMessage(Message(chatIdentifier,name,default_message,time,"0"))

        }


    }

    }

}
