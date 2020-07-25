package com.binar.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.binar.rockpaperscissors.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var you: String = ""
    private var com: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // REMOVE ACTION BAR
        this.supportActionBar?.hide()

        binding.ivYouRock.setOnClickListener {
            you = "rock"
            tv_reset.visibility = View.VISIBLE
            changeYouIcons(you)
        }
        binding.ivYouPaper.setOnClickListener {
            you = "paper"
            tv_reset.visibility = View.VISIBLE
            changeYouIcons(you)
        }
        binding.ivYouScissors.setOnClickListener {
            you = "scissors"
            tv_reset.visibility = View.VISIBLE
            changeYouIcons(you)
        }
        binding.tvReset.setOnClickListener {
            resetYouIcons()
            resetComIcons()
            tv_status.setText(R.string.vs)
            tv_reset.visibility = View.GONE
        }
    }

    private fun resetYouIcons() {
        iv_you_rock.setBackgroundResource(R.drawable.ic_stroke)
        iv_you_paper.setBackgroundResource(R.drawable.ic_stroke)
        iv_you_scissors.setBackgroundResource(R.drawable.ic_stroke)
    }

    private fun resetComIcons() {
        iv_com_rock.setBackgroundResource(R.drawable.ic_stroke)
        iv_com_paper.setBackgroundResource(R.drawable.ic_stroke)
        iv_com_scissors.setBackgroundResource(R.drawable.ic_stroke)
    }

    private fun changeYouIcons(you: String) {
        when (you) {
            "rock" -> {
                resetYouIcons()
                iv_you_rock.setBackgroundResource(R.drawable.ic_fill)
                showResult(matchingCondition(you, comRandom()))
            }
            "paper" -> {
                resetYouIcons()
                iv_you_paper.setBackgroundResource(R.drawable.ic_fill)
                showResult(matchingCondition(you, comRandom()))
            }
            "scissors" -> {
                resetYouIcons()
                iv_you_scissors.setBackgroundResource(R.drawable.ic_fill)
                showResult(matchingCondition(you, comRandom()))
            }
            else -> Log.d("ERROR", "You Neither Rock Paper or Scissors")
        }
    }

    private fun changeComIcons(com: String) {
        when (com) {
            "rock" -> {
                resetComIcons()
                iv_com_rock.setBackgroundResource(R.drawable.ic_fill)
            }
            "paper" -> {
                resetComIcons()
                iv_com_paper.setBackgroundResource(R.drawable.ic_fill)
            }
            "scissors" -> {
                resetComIcons()
                iv_com_scissors.setBackgroundResource(R.drawable.ic_fill)
            }
            else -> Log.d("ERROR", "Com Neither Rock Paper or Scissors")
        }
    }

    private fun comRandom() : String {
        val comArray: Array<String> = arrayOf("rock", "paper", "scissors")
        com = comArray.random()
        changeComIcons(com)
        return com
    }

    private fun matchingCondition(you: String, com: String) : String {
        return if (you == com) "draw"
          else if ((you == "rock" && com == "paper") || (com == "rock" && you == "paper")) {
            if (you == "paper") "win" else "lose"
        } else if ((you == "rock" && com == "scissors") || (com == "rock" && you == "scissors")) {
            if (you == "rock") "win" else "lose"
        } else if ((you == "paper" && com == "scissors") || com == "paper" && you == "scissors") {
            if (you == "scissors") "win" else "lose"
        } else "error"
    }

    private fun showResult(result: String) {
        when (result) {
            "draw" -> {
                tv_status.setText(R.string.draw)
                Log.d("RESULT", "You chose ${you}, COM chose ${com}. DRAW !!")
            }
            "win" -> {
                tv_status.setText(R.string.win)
                Log.d("RESULT", "You chose ${you}, COM chose ${com}. YOU WIN !!")
            }
            "lose" -> {
                tv_status.setText(R.string.lose)
                Log.d("RESULT", "You chose ${you}, COM chose ${com}. YOU LOSE !!")
            }
            else -> Log.d("ERROR", "Neither Win Lose or Draw")
        }
    }
}