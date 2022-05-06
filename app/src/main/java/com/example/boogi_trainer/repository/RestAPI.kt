package com.example.boogi_trainer.repository

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
data class ResponseUser(val user:User?)
data class ResponseUserLog(val userLog:UserLog?)
data class Message(val message: String)

data class User(var uid: String?="",
                @SerializedName("body_form")
                var bodyForm:String?="",
                var height:Double?=0.0,
                var name: String?="",
                var password:String?="",
                var weight:Double?=0.0)
data class UserLog(val uid: String?,
                   val dates:ArrayList<DateLog>?)

data class DateLog(val date: String?,
                val exercises:ArrayList<Exercise>?,
                val meals:ArrayList<Meal>?,
                @SerializedName("diet_info")
                val dietInfo: DietInfo?
)
data class Exercise(
    val exercise: String? = "",
    val reps: Int? = 0
)
data class Meal(
    val food: String? = "",
    val size: String? = "",
    val kind: String? = "",
    val kcal: Double? = 0.0,
    val carbs: Double? = 0.0,
    val protein: Double? = 0.0,
    val fat: Double? = 0.0
)
data class PostMeal(
    val food: String? = "",
    val size: String? = "",
    val kind: String? = ""
)
data class DietInfo(
    @SerializedName("intake_kcal")
    val intakeKcal: Double? = 0.0,
    @SerializedName("burned_kcal")
    val burnedKcal: Double? = 0.0,
    @SerializedName("exercise_time")
    val exerciseTime: Int? = 0,
    val weight: Double? = 0.0,
    @SerializedName("intake_carbs")
    val intakeCarbs: Double? = 0.0,
    @SerializedName("intake_protein")
    val intakeProtein: Double? = 0.0,
    @SerializedName("intake_fat")
    val intakeFat: Double? = 0.0
)
data class TodayInfo(
    var breakfastKcal: Double = 0.0,
    var breakfastCarbohydrate: Double = 0.0,
    var breakfastProtein: Double = 0.0,
    var breakfastFat: Double = 0.0,
    var lunchKcal: Double = 0.0,
    var lunchCarbohydrate: Double = 0.0,
    var lunchProtein: Double = 0.0,
    var lunchFat: Double = 0.0,
    var dinnerKcal: Double = 0.0,
    var dinnerCarbohydrate: Double = 0.0,
    var dinnerProtein: Double = 0.0,
    var dinnerFat: Double = 0.0
)

interface RestAPI {
    @GET("userLogs/{uid}")
    fun getUserLog(@Path("uid") uid:String):Call<ResponseUserLog>


    @GET("users/{uid}")
    fun getUser(@Path("uid") uid:String):Call<ResponseUser>

    @POST("userLogs/{uid}/{date}/meals")
    fun postMeal(@Path("uid") uid:String, @Path("date") date:String, @Body payload:PostMeal):Call<Message>

    @POST("userLogs/{uid}/{date}/exercises")
    fun postExercise(@Path("uid") uid:String, @Path("date") date:String, @Body payload:Exercise):Call<Message>
}