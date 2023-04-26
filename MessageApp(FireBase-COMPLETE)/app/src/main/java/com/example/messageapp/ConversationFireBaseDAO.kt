package com.example.messageapp

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.firebase.database.*


class ConversationFireBaseDAO:ConversationDAO {


    private var context: Context
    private  lateinit var dbRef: DatabaseReference //get the reference of firebase data base
    private lateinit  var adapter: ConversationAdapter




    constructor(ctx:Context){
       context=ctx
    }

    override fun insertConversation(std: Conversation) {

        dbRef= FirebaseDatabase.getInstance().getReference("Conversation")
        val iD=dbRef.push().key!!
        val conversation=Conversation(std.usernum,std.name,std.reply,std.senderphone)
        val status= dbRef.child(iD!!).setValue(conversation)


        status.addOnSuccessListener {

//            Toast.makeText(context,"Record  Saved in Conversation Table", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(context,"Record Not Saved in Conversation Table", Toast.LENGTH_SHORT).show()
        }



        }


    @SuppressLint("SuspiciousIndentation")
    override fun readConversation(senderphone:String?,callback: (conversationList: MutableList<Conversation>) -> Unit) {
        val conversationList = mutableListOf<Conversation>()




        dbRef=FirebaseDatabase.getInstance().getReference("Conversation")


//        val query=dbRef.orderByChild("senderphone").startAt(senderphone).endAt(senderphone + "\uf8ff")

           dbRef. addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
//                val childCount = snapshot.childrenCount
//                Toast.makeText(context,"NUMBER OF NODES IN DATABASE $childCount",Toast.LENGTH_SHORT).show()


                conversationList.clear() //TESTING PHASE
                if(snapshot.exists()){
                    for(i in snapshot.children){ //Iterate over all the child nodes

                        val receiverphone = i.child("usernum").value.toString().trim()
                        val sendernumber = i.child("senderphone").value.toString().trim()

                        if (receiverphone == senderphone||sendernumber==senderphone) {
                            val con = i.getValue(Conversation::class.java) //fetches a particular node from the database

                            conversationList.add(con!!) //add the node to the arraylist !! ensures that the object is not null
                        }
                       // adapter.notifyDataSetChanged()
                    }



                }
                callback(conversationList)
            }


            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(context,"FireBase!! Failed to Read Conversation", Toast.LENGTH_SHORT).show()
            }


        })

//        if(conversationList.size==0){
//            Toast.makeText(context,"Nothing store" +
//                    "d in the conversation List", Toast.LENGTH_SHORT).show()
//        }
//        return conversationList
    }




}