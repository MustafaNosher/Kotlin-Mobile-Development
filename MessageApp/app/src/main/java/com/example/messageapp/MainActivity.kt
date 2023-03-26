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


class MainActivity : AppCompatActivity() {




    private var messageList = mutableListOf<ChatContent>()

    private lateinit var rv:RecyclerView //recycler view type object
    private lateinit var customadapter:MyAdapter //adapter type object
    private lateinit var textInput: EditText // Edit Text type object
    private lateinit var sendButton: Button // Button Type Object
    private lateinit var dbHelper:DataBaseHandler
    private lateinit var name:TextView
    private lateinit var bot_name:TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        sendButton=findViewById(R.id.button) // id of send button that user will click to send the message


        rv=findViewById(R.id.recyclerView)
        rv.layoutManager=LinearLayoutManager(this)

//        initView()

///        time=findViewById(R.id.textView4)



        dbHelper= DataBaseHandler(this)


       sendButton.setOnClickListener{ sendChat() }


    }
    private fun addConversation(){
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.message_layout, null)
        name=view.findViewById(R.id.textView)
        bot_name=view.findViewById(R.id.textView11)
//        setContentView(view)

        val username=name.text.toString()
        val robot=bot_name.text.toString()
        if(username.isEmpty() || robot.isEmpty()){
            Toast.makeText(this,"Data retrieved seems to be Empty",Toast.LENGTH_SHORT).show()
        }
        else{
            val ctd=Conversation(username,robot)
            val status=dbHelper.insertConversation(ctd)

            if(status>-1){
                Toast.makeText(this,"Conversation Added",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Record Not Saved",Toast.LENGTH_SHORT).show()
            }
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
            addConversation()

        }

    }
//    private fun initView(){
//        name=findViewById(R.id.textView)
//        time=findViewById(R.id.textView4)
//    }
}
