package com.example.caloriecounter

data class Daily(var currentdate:Int?=null,var currentday:String?=null, var currentmonth:String?=null,var foodname:String?=null,var calories:Long=0){


}

interface DailyDAO {
    fun insertCalories(std: Daily)
    fun readCalories(iD:Int?,month:String?,callback: (dailyList: MutableList<Daily>) -> Unit)
    fun UpdateCalories(iD:Int?, callback: (Sum: Int) -> Unit)

}
