package com.example.messageapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.google.firebase.database.*


class ConversationFireBaseDAO:ConversationDAO {


    private var context: Context
    private  lateinit var dbRef: DatabaseReference //get the reference of firebase data base


    constructor(ctx:Context){
       context=ctx
    }

    override fun insertConversation(std: Conversation) {

        dbRef= FirebaseDatabase.getInstance().getReference("Conversation")
        val iD=dbRef.push().key!!
        val conversation=Conversation(std.usernum,std.name,std.reply,std.senderphone)
        val status= dbRef.child(iD!!).setValue(conversation)


        status.addOnSuccessListener {

            Toast.makeText(context,"Record  Saved in Conversation Table", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(context,"Record Not Saved in Conversation Table", Toast.LENGTH_SHORT).show()
        }

        //        lateinit var db: SQLiteDatabase
//
//
//        db=dbHelper.writableDatabase
//
//        val contentValues = ContentValues()
//        contentValues.put(DataBaseHandler.ID,std.usernum)
//        contentValues.put(DataBaseHandler.USER, std.name)
//        contentValues.put(DataBaseHandler.BOT, std.reply)
//        val status = db.insert(DataBaseHandler.TABLE_NAME, null, contentValues)

        }


    override fun readConversation(senderphone:String?): MutableList<Conversation> {
        val conversationList = mutableListOf<Conversation>()
        dbRef=FirebaseDatabase.getInstance().getReference("Conversation")

        val query=dbRef.orderByChild("senderphone").equalTo(senderphone)

        query.addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                conversationList.clear() //TESTING PHASE
                if(snapshot.exists()){
                    for(i in snapshot.children){ //Iterate over all the child nodes

                        val con=i.getValue(Conversation::class.java) //fetches a particular node from the database

                        conversationList.add(con!!) //add the node to the arraylist !! ensures that the object is not null

                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(context,"FireBase!! Failed to Read Conversation", Toast.LENGTH_SHORT).show()
            }


        })

//        lateinit var db:SQLiteDatabase
//
////        val db = this.readableDatabase
//        db=dbHelper.readableDatabase
//
//        val sql = "Select * from ${DataBaseHandler.TABLE_NAME}"
//        val fetch = db.rawQuery(sql, null)
//        while (fetch.moveToNext()) {
//            val id=fetch.getString(0).toInt()
//            val sendername=fetch.getString(1)
//            val recievername=fetch.getString(2)

//            conversationList.add(Conversation(id,sendername,recievername))
//      }
//        fetch.close()
//        db.close()
        if(conversationList.size==0){
            Toast.makeText(context,"Nothing stored in the conversation List", Toast.LENGTH_SHORT).show()
        }
        return conversationList
    }

}