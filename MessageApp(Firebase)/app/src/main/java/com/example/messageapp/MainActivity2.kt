package com.example.messageapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {


    private lateinit var rv: RecyclerView //recycler view type object
    private lateinit var adapter:ConversationAdapter //adapter type object
    private lateinit var textInput: EditText // Edit Text type object
    private lateinit var addButton: Button // Button Type Object
    private lateinit var CDAO:  ConversationDAO//MESSAGE domain layer object



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        CDAO=ConversationFireBaseDAO(this)
        var conversation_list=CDAO.readConversation()



        rv=findViewById(R.id.ContactsRecyclerView)
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rv.layoutManager= LinearLayoutManager(this)

         adapter=ConversationAdapter(conversation_list)


        rv.adapter = adapter

        rv.scrollToPosition(conversation_list.size - 1)

        adapter.notifyDataSetChanged()

        //Intent to send the name and ID of the person to the message activity

        adapter.setOnItemClickListener(object:ConversationAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

                val intent=Intent(this@MainActivity2,MainActivity::class.java)
                    intent.putExtra("ID", conversation_list[position].usernum)
                    intent.putExtra("NAME",conversation_list[position].reply)
                    startActivity(intent)
            }

        })




        addButton=findViewById(R.id.addButton)
        addButton.setOnClickListener {

            textInput=findViewById(R.id.myContactTextBox)

            val size=conversation_list.size
            if(conversation_list.size>1){
                Toast.makeText(this,"Conversation List has size $size", Toast.LENGTH_SHORT).show()
            }

            val rName = textInput.text.toString().trim() //name of the receiver

            //Save the name of the person in the data base with whom the user wants to start conversation

            if(rName.isNotEmpty()){

                var id=conversation_list.size

                conversation_list.add(Conversation(id,"MustafaNosher",rName))
                textInput.text.clear()
                rv.scrollToPosition(conversation_list.size - 1)
                adapter.notifyDataSetChanged()
              CDAO.insertConversation(Conversation(id,"MustafaNosher",rName))
            }
            else{

                Toast.makeText(this,"Field Cannot be Empty!", Toast.LENGTH_SHORT).show()
            }


    }


    }
}