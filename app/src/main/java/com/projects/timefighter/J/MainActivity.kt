package com.projects.timefighter.J

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScore: TextView
    internal lateinit var timeLeftTextView: TextView

    internal var score = 0

    internal var gameStarted = false

    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 50000
    internal val countDownInterval: Long = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById(R.id.TapMe)
        gameScore = findViewById(R.id.gameScore)
        timeLeftTextView = findViewById(R.id.timeLeft)

        tapMeButton.setOnClickListener { view ->
            incrementScore()
        }

        resetGame()
    }

    private fun incrementScore() {

        if (!gameStarted) {
            startGame()
        }

        score += 1
        val newScore = getString(R.string.your_score, score)
        gameScore.text = newScore
    }

    private fun resetGame() {
        score = 0

        gameScore.text = getString(R.string.your_score, score)

        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_left, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUnitlFinished: Long) {
                val timeLeft = millisUnitlFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft)

            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.gameOverMessage, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}



