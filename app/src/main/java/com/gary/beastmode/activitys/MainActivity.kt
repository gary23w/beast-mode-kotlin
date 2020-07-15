package com.gary.beastmode.activitys


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.core.view.GravityCompat
import com.gary.beastmode.R
import com.gary.beastmode.data.Prefs
import com.gary.beastmode.data.constant
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    var prefs: Prefs? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        navigationViewMain.itemIconTintList = null
        toolbar_main_activity?.setTitle("")
        nav_view.setNavigationItemSelectedListener(this)
        setupActionBar()
        llstart.setOnClickListener {
            val intent = Intent(this, ExcerciseActivity::class.java)
            startActivity(intent)
            finish()
        }
        navigationViewMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.exerciseList-> {
                    val intent = Intent(this, ExerciseSuggestActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.BeastMode-> {
                    constant.WORKOUTTIME = "60"
                    constant.RESTTIME = "5"
                    constant.INTERVALS = "10"
                    val intent = Intent(this, ExcerciseActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.share_main_btn-> {
                    shareThisApp()
                }

            }
            false
        }
    }
    private fun setupActionBar() {
        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)
        toolbar_main_activity.setNavigationOnClickListener {
            toggleDrawer()
        }
    }

    private fun toggleDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.myStats -> {
                val intent = Intent(this, StatsActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.bmiCalculate -> {
                val intent = Intent(this, BmiCalculator::class.java)
                startActivity(intent)
                finish()
            }
            R.id.favorites -> {
                val intent = Intent(this, FavActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.beginnerIntervals -> {
                constant.WORKOUTTIME = "30"
                constant.RESTTIME = "30"
                constant.INTERVALS = "3"
                val intent = Intent(this, ExcerciseActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.intermediateIntervals -> {
                constant.WORKOUTTIME = "40"
                constant.RESTTIME = "20"
                constant.INTERVALS = "5"
                val intent = Intent(this, ExcerciseActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.advancedIntervals -> {
                constant.WORKOUTTIME = "45"
                constant.RESTTIME = "15"
                constant.INTERVALS = "5"
                val intent = Intent(this, ExcerciseActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.expertIntervals -> {
                constant.WORKOUTTIME = "60"
                constant.RESTTIME = "10"
                constant.INTERVALS = "7"
                val intent = Intent(this, ExcerciseActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.tenminHell -> {
                constant.WORKOUTTIME = "60"
                constant.RESTTIME = "5"
                constant.INTERVALS = "10"
                val intent = Intent(this, ExcerciseActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}



