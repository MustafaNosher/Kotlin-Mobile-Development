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
    private lateinit var adapter: ConversationAdapter //adapter type object
    private lateinit var textInput: EditText // Edit Text type object
    private lateinit var textInput2: EditText // Edit Text type object
    private lateinit var addButton: Button // Button Type Object
    private lateinit var CDAO: ConversationDAO//MESSAGE domain layer object


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val sendername = intent.getStringExtra("NAME2")
        val senderphone =
            intent.getStringExtra("PHONE")  //Receives the name of the person with whom to began the conversation


        CDAO = ConversationFireBaseDAO(this)

        rv = findViewById(R.id.ContactsRecyclerView)
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rv.layoutManager = LinearLayoutManager(this)

        var conversation_list = CDAO.readConversation(senderphone)




        adapter = ConversationAdapter(senderphone, conversation_list)


        rv.adapter = adapter


        rv.scrollToPosition(conversation_list.size - 1)
        adapter.notifyDataSetChanged()


        //Intent to send the name and ID of the person to the message activity

        adapter.setOnItemClickListener(object : ConversationAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

                val intent = Intent(this@MainActivity2, MainActivity::class.java)
                intent.putExtra("ID", conversation_list[position].usernum)
                intent.putExtra("NAME", conversation_list[position].reply)
                intent.putExtra("SENDER", sendername)
                intent.putExtra("SENDERCELL", senderphone)
                startActivity(intent)
            }

        })





        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {

            textInput = findViewById(R.id.myContactTextBox)
            textInput2 = findViewById(R.id.Phone)


            val phone_number = textInput2.text.toString().trim()
//            val phonelong=phone_number.toLong()

            val size = conversation_list.size
//            if(conversation_list.size>1){
//                Toast.makeText(this,"Conversation List has size $size", Toast.LENGTH_SHORT).show()
//            }

            var rName = textInput.text.toString().trim() //name of the receiver
//            var lower=rName.lowercase()

            var flag: Boolean = false

            for (i in conversation_list) {
                var x = i.usernum //Phone Number of the User
                if (x != null) {
                    if (x == phone_number) {
                        flag = true
                        break
                    }
                }

            }

            //Save the name of the person in the data base with whom the user wants to start conversation
            if (!flag) {
                if (rName.isNotEmpty() && phone_number.toString()
                        .isNotEmpty() && phone_number.length == 11
                ) {


                    conversation_list.add(Conversation(phone_number, sendername, rName, senderphone))

                    textInput.text.clear()
                    textInput2.text.clear()
                    rv.scrollToPosition(conversation_list.size - 1)
                    adapter.notifyDataSetChanged()

                    CDAO.insertConversation(
                        Conversation(
                            phone_number,
                            sendername,
                            rName,
                            senderphone
                        )
                    )
                } else if (rName.isEmpty() || phone_number.isEmpty()) {

                    Toast.makeText(this, "Field Cannot be Empty!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Phone Number Should be of 11 Digits!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Enter Unique PhoneNumber", Toast.LENGTH_SHORT).show()

            }

        }
    }
}





