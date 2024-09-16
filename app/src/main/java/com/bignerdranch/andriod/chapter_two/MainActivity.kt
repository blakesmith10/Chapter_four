package com.bignerdranch.andriod.chapter_two

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.andriod.chapter_two.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create a common click listener for advancing to the next question
        val nextQuestionListener = View.OnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        val previousQuestionListener = View.OnClickListener {
            currentIndex = if (currentIndex - 1 < 0) {
                questionBank.size - 1
            } else {
                currentIndex - 1
            }
            updateQuestion()
        }

        // Set click listeners for buttons
        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener(nextQuestionListener)

        // Set click listener for the TextView to advance to the next question
        binding.questionTextView.setOnClickListener(nextQuestionListener)

        binding.previousButton.setOnClickListener(previousQuestionListener)

        updateQuestion()
    }



    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
