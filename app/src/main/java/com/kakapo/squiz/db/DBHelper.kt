package com.kakapo.squiz.db

import android.annotation.SuppressLint
import android.content.Context
import com.kakapo.squiz.model.Category
import com.kakapo.squiz.model.Question
import  com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class DBHelper(context: Context) : SQLiteAssetHelper(context, DB_NAME, null, DB_VER){
    companion object{
        private var instance: DBHelper? = null
        private const val DB_NAME = "EDMTQuiz2019.db"
        private const val DB_VER = 1

        @Synchronized
        fun getInstance(context: Context) : DBHelper{
            if(instance == null) instance = DBHelper(context)
            return instance!!
        }
    }

    val allCategory: MutableList<Category>
        @SuppressLint("Recycle")
        get() {
            val db = instance!!.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM Category", null)
            val categories = ArrayList<Category>()
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast){
                    val category = Category(
                        cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("Image"))
                    )

                    categories.add(category)
                    cursor.moveToNext()
                }
            }
            cursor.close()
            db.close()


            return categories
        }

    fun getQuestionByCategory(categoryId: Int): MutableList<Question>{
        val db = instance!!.writableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Question WHERE categoryId = $categoryId ORDER BY RANDOM() LIMIT 30",
            null
        )
        val questionList = ArrayList<Question>()
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast){
                val question = Question(
                        cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("QuestionText")),
                        cursor.getString(cursor.getColumnIndex("QuestionImage")),
                        cursor.getString(cursor.getColumnIndex("AnswerA")),
                        cursor.getString(cursor.getColumnIndex("AnswerB")),
                        cursor.getString(cursor.getColumnIndex("AnswerC")),
                        cursor.getString(cursor.getColumnIndex("AnswerD")),
                        cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                        if (cursor.getInt(cursor.getColumnIndex("isImageQuestion")) == 0)
                            FALSE else TRUE ,
                        cursor.getInt(cursor.getColumnIndex("CategoryId"))

                )
                questionList.add(question)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        return questionList
    }
}