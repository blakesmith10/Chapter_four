package com.bignerdranch.andriod.chapter_two

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

   /* init {

        Log.d(TAG, "ViewModel instance created")

    }

    override fun onCleared() {

        Log.d(TAG, "ViewModel instance about to be destroyed")


    }
    */
   private val questionBank = listOf(
       Question(R.string.question_australia, true),
       Question(R.string.question_oceans, true),
       Question(R.string.question_mideast, false),
       Question(R.string.question_africa, false),
       Question(R.string.question_americas, true),
       Question(R.string.question_asia, true)
   )

    private var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer:Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        Log.d(TAG, "Updating question text")
        currentIndex = (currentIndex + 1) % questionBank.size
        Log.d(TAG, "Current question index: $currentIndex")

      //  currentIndex = (currentIndex + 1 ) % questionBank.size
    }

}