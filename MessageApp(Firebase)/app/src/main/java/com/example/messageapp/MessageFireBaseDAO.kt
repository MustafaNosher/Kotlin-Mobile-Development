package com.example.messageapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.google.firebase.database.*

class MessageFireBaseDAO:MessageDao{

    private  lateinit var  context: Context
    private lateinit var dbRef:DatabaseReference //get the reference of firebase data base

    constructor(ctx:Context){
        context=ctx
    }

    override fun insertMessage(msg:Message){

        dbRef=FirebaseDatabase.getInstance().getReference("Message")
        val conversationID=dbRef.push().key!!
        val message=Message(msg.conversation_id,msg.name,msg.message_body,msg.time_stamp,msg.status)
       val status= dbRef.child(conversationID!!).setValue(message)


//        val contentValues = ContentValues()
//
//        contentValues.put("${DataBaseHandler.CONVERSATION}",msg.conversation_id)
//        contentValues.put("${DataBaseHandler.NAME}",msg.name)
//        contentValues.put("${DataBaseHandler.body}",msg.message_body)
//        contentValues.put("${DataBaseHandler.TIMESTAMP}",msg.time_stamp)
//        contentValues.put("${DataBaseHandler.STATUS}",msg.status)
//
//        val status=db.insert(DataBaseHandler.TABLE_NAME2,null,contentValues)
//        db.close()

        status.addOnSuccessListener {

            Toast.makeText(context,"Record  Saved in Message Table", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{

            Toast.makeText(context,"Record  Not Saved in Message Table", Toast.LENGTH_SHORT).show()

        }




    }
    override fun readmessage(id:String?):MutableList<Message>{

        val messageList = mutableListOf<Message>()
        dbRef=FirebaseDatabase.getInstance().getReference("Message")

        val query=dbRef.orderByChild("conversation_id").equalTo(id)

        query.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()
                if(snapshot.exists()){
                    for(i in snapshot.children){

                        val message=i.getValue(Message::class.java) //fetches a particular node from the database

                        messageList.add(message!!) //add the node to the arraylist !! ensures that the object is not null

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(context,"FireBase!! Failed to Read Messages", Toast.LENGTH_SHORT).show()
            }

        })



//
        return messageList

    }


}

