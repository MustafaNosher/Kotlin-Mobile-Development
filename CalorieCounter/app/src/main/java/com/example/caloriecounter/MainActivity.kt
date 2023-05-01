package com.example.caloriecounter

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.caloriecounter.MainActivity.MyClass.Companion.total2
import java.util.*

var Total:Long? = 0

class MainActivity : AppCompatActivity() {

    private lateinit var etFoodName: EditText
    private lateinit var etCalories: EditText
    private lateinit var tvProgress: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var historybutton: Button
    private lateinit var savebutton: Button
    private lateinit var DDAO:  DailyDAO //MESSAGE domain layer object
//    private lateinit var obj:Daily



    class MyClass {
        companion object {
            var total2: Int = 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var daily_list = mutableListOf<Daily>()



        etFoodName = findViewById(R.id.edit_text_food_name)
        etCalories = findViewById(R.id.edit_text_calories)
        tvProgress = findViewById(R.id.text_view_progress)
        progressBar = findViewById(R.id.progress_bar)
        progressBar.max = 2000 // set max value of progress bar
        historybutton=findViewById(R.id.button_history)
        savebutton=findViewById(R.id.button_save)
        DDAO=DailyFireBaseDAO(this)

        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) //Returns name of the day
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) //Return date
        val month = calendar.get(Calendar.MONTH) //Returns name of month

        //The max value will be set by user which will be recieved through intent in this activity





        val max_calories=progressBar.max

       DDAO.UpdateCalories(dayOfMonth) { sum ->
           progressBar.progress = sum
           tvProgress.text = "$sum kcal of \n${progressBar.max}  kcal"

           if(sum>max_calories) {

               Toast.makeText(this,"You Have  Already Consumed your Daily Calories!!!",Toast.LENGTH_SHORT).show()
               }
           total2=sum
       }








        savebutton.setOnClickListener {
            val foodname=etFoodName.text.toString().trim()
            val count=etCalories.text.toString().trim()
            if (foodname.isNotEmpty() && count.isNotEmpty()) {
                val calories = count.toIntOrNull() ?: 0
                var grand=total2+calories
                Toast.makeText(this,"Total 2 vlaue $total2",Toast.LENGTH_SHORT).show()


                tvProgress.text = "$grand kcal of \n" +
                        "${progressBar.max}  kcal"
                progressBar.progress = grand

                if(grand>max_calories) {
                    Toast.makeText(this,"You Have Consumed your Daily Calories!",Toast.LENGTH_SHORT).show()


                }
                etCalories.text.clear()
                etFoodName.text.clear()




                val day:String=getDayOfWeek(dayOfWeek)
                val monthname:String=getMonthName(month)
                val consumed=count.toLong()

                Log.d("Hello","$day,$monthname,$dayOfMonth")

                daily_list.add( Daily(dayOfMonth,day,monthname,foodname,consumed))

                DDAO.insertCalories( Daily(dayOfMonth,day,monthname,foodname,consumed))


            }
            else{
                Toast.makeText(this,"Fields cannot be left Empty!",Toast.LENGTH_SHORT).show()
            }



        }
        historybutton.setOnClickListener {


            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putExtra("currentdate", dayOfMonth)
            startActivity(intent)

        }


    }

    private fun getDayOfWeek(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> throw IllegalArgumentException("Invalid day of week: $dayOfWeek")
        }
    }
    private fun getMonthName(month: Int): String {
        return when (month) {
            0 -> "january"
            1 -> "february"
            2 -> "march"
            3 -> "april"
            4 -> "may"
            5 -> "june"
            6 -> "july"
            7 -> "august"
            8 -> "september"
            9 -> "october"
            10 -> "november"
            11 -> "december"
            else -> throw IllegalArgumentException("Invalid month value: $month")
        }
    }

}
