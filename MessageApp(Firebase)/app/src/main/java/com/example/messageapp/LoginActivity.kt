package com.example.messageapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var sendername: EditText // Edit Text type object
    private lateinit var senderphone: EditText // Edit Text type object
    private lateinit var login: Button // Button Type Object


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sendername=findViewById(R.id.sendername1)
        senderphone=findViewById(R.id.editTextPhoneNumber1)
        login=findViewById(R.id.buttonLogin1)



        login.setOnClickListener {
            var name=sendername.text.toString().trim()
            var phone=senderphone.text.toString().trim()

        if(name.isNotEmpty() && phone.isNotEmpty()) {


                val intent = Intent(this@LoginActivity, MainActivity2::class.java)
                intent.putExtra("NAME2", name)
                intent.putExtra("PHONE",phone)
                startActivity(intent)

            }
        else{
            Toast.makeText(this, "Field Cannot be Empty!", Toast.LENGTH_SHORT).show()
        }
        }

    }
}