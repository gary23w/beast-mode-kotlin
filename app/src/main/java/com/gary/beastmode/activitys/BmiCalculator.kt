package com.gary.beastmode.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.gary.beastmode.R
import com.gary.beastmode.data.Prefs
import com.gary.beastmode.data.constant
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar
import java.math.RoundingMode
import java.text.DecimalFormat


class BmiCalculator : BaseActivity() {
    var prefs: Prefs? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = Prefs(this)
        setContentView(R.layout.activity_bmi_calculator)
        setSupportActionBar(toolbar_exercise_bmi)
        if (prefs?.bmiSharedPref != null) {
            tvBMI.text = prefs?.bmiSharedPref.toString()
            setColorCode(prefs?.bmiSharedPref!!)
        }
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        checkStats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
            finish()
        }
        toolbar_exercise_bmi.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        sbWeight.setOnProgressChangeListener(BMIProgressChangeListener(tvWeight, getString(R.string.unit_kg)))
        sbHeight.setOnProgressChangeListener(BMIProgressChangeListener(tvHeight, getString(R.string.unit_cm)))
        calculateBMInow.setOnClickListener {
            val weight = sbWeight.progress.toDouble()
            val height = sbHeight.progress.toDouble() / 100
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.HALF_UP
            val bmiDetails = weight / (height * height)
            df.format(bmiDetails)
            tvBMI.text = bmiDetails.toInt().toString()
            constant.BMISHAREDCONSTANT = bmiDetails.toInt()
            prefs?.bmiSharedPref = bmiDetails.toInt()
            setColorCode(bmiDetails.toInt())
        }
    }
    inner class BMIProgressChangeListener(var tv: TextView, var unit: String) : DiscreteSeekBar.OnProgressChangeListener {
        override fun onProgressChanged(seekBar: DiscreteSeekBar?, value: Int, fromUser: Boolean) {
            tv.text = value.toString()
        }
        override fun onStartTrackingTouch(seekBar: DiscreteSeekBar?) {
        }
        override fun onStopTrackingTouch(seekBar: DiscreteSeekBar?) {
        }
    }
    private fun setColorCode(int: Int) {
        when (int) {
            in 0..18 -> {
                tvResult.text = "HEALTHY"
                tvResult.setBackgroundResource(R.color.blue)
            }
            in 19..24 -> {
                tvResult.text = "AVERAGE"
                tvResult.setBackgroundResource(R.color.green)
            }
            in 25..29 -> {
                tvResult.text = "BELOW AVERAGE"
                tvResult.setBackgroundResource(R.color.yellow)
            }
            in 30..39 -> {
                tvResult.text = "Lets get to work"
                tvResult.setBackgroundResource(R.color.orange)
            }
            else -> {
                tvResult.setBackgroundResource(R.color.red)
            }
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

