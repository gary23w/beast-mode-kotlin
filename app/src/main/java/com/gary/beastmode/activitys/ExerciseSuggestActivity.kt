package com.gary.beastmode.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gary.beastmode.R
import com.gary.beastmode.activitys.adapter.ExerciseAdapter
import kotlinx.android.synthetic.main.activity_exercise_suggest.*

class ExerciseSuggestActivity : AppCompatActivity() {
    private val exerciseAdapter = ExerciseAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_suggest)
        exerciseList_suggest.apply {
            layoutManager = LinearLayoutManager(this@ExerciseSuggestActivity)
            adapter = exerciseAdapter
                exerciseAdapter.onAddNewsItem(null)
        }
        toolbar_list_suggest.setTitle("Need an idea?")
        setupActionBar()
    }
    private fun setupActionBar() {
        setSupportActionBar(toolbar_list_suggest)
        toolbar_list_suggest.setNavigationIcon(R.drawable.ic_go_back)
        toolbar_list_suggest.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}