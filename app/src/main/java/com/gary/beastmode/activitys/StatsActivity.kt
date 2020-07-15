package com.gary.beastmode.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gary.beastmode.R
import com.gary.beastmode.data.Prefs
import com.gary.beastmode.data.constant
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {
    var prefs: Prefs? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        prefs = Prefs(this)
        statsText.text = prefs?.intervalPrefCount.toString()
        yourBMI.text = prefs?.bmiSharedPref.toString()
        quitButtonPREF.text = prefs?.quitButtonPref.toString()
        when (prefs?.bmiSharedPref) {
            in 0..18 -> {
                amIhealthy.text = "HEALTHY"
                amIhealthy.setBackgroundResource(R.color.blue)
            }
            in 19..24 -> {
                amIhealthy.text = "AVERAGE"
                amIhealthy.setBackgroundResource(R.color.green)
            }
            in 25..29 -> {
                amIhealthy.text = "BELOW AVERAGE"
                amIhealthy.setBackgroundResource(R.color.yellow)
            }
            in 30..39 -> {
                amIhealthy.text = "Lets get to work"
                amIhealthy.setBackgroundResource(R.color.orange)
            }
            else -> {
                amIhealthy.setBackgroundResource(R.color.red)
            }

        }

        setSupportActionBar(toolbar_exercise_activity_stats)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity_stats.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    }

