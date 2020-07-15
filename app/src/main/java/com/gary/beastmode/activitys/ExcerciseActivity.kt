package com.gary.beastmode.activitys

import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.util.Rational
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.ViewModelProviders
import com.gary.beastmode.R
import com.gary.beastmode.data.Prefs
import com.gary.beastmode.data.constant
import com.gary.beastmode.room.viewmodel.FavoriteViewModel
import com.gary.news.model.Favorite
import kotlinx.android.synthetic.main.activity_excercise.*


class ExcerciseActivity : BaseActivity() {
    lateinit var viewModel: FavoriteViewModel
    var mediaPlayer: MediaPlayer? = null
    var timeMil: Long = 0
    var restTimer: CountDownTimer? = null
    var intervalCounter = 1
    var prefs: Prefs? = null
    var restProgress = 0
    var exerciseTimer: CountDownTimer? = null
    var exerciseProgress = 0
    var started = false
    fun goPip(view: View?) {
        llRestView.visibility = View.GONE
        val d = windowManager
            .defaultDisplay
        val p = Point()
        d.getSize(p)
        val width: Int = p.x
        val height: Int = p.y
        val ratio = Rational(width, height)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val pip_Builder = PictureInPictureParams.Builder()
            pip_Builder.setAspectRatio(ratio).build()
            enterPictureInPictureMode(pip_Builder.build())
        } else {
            showSnackBar("Please update your phone.")
        }

    }
    fun start(view: View) {
        started = true
        intervalCountExercise.visibility = View.VISIBLE
        llRestView.visibility = View.GONE
        startFrame.visibility = View.VISIBLE
        if (workoutTime!!.text.toString().length  == 0) {
            showSnackBar("No info.")
            if (restTime!!.text.toString().length == 0) {
                showSnackBar("No info.")
            }
        } else {
            showSnackBar("Get ready!")
            setStart()
            mediaPlayer?.start()
            quit.visibility = View.VISIBLE
        }
    }
    fun quit(view: View) {
        prefs!!.quitButtonPref = prefs!!.quitButtonPref!! + 1
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise)
        prefs = Prefs(this)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
        if (constant.WORKOUTTIME.isNotEmpty()) {
            workoutTime.text = constant.WORKOUTTIME.toEditable()
            restTime.text = constant.RESTTIME.toEditable()
            intervalCountText.text = constant.INTERVALS.toEditable()
        } else if(prefs!!.workoutPref?.isNotEmpty()!!) {
            workoutTime.text = prefs!!.workoutPref?.toEditable()
            restTime.text = prefs!!.restPref?.toEditable()
            intervalCountText.text = prefs!!.intervalPref?.toEditable()
        }
        ///// BEEPS////////////////////////////////////////////////////////////////////
        mediaPlayer = MediaPlayer.create(this,
            R.raw.beeps
        )
        mediaPlayer?.setWakeMode(this@ExcerciseActivity, 1)
        setVolumeControlStream(AudioManager.STREAM_ALARM)

        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC))

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                newVolume: Int,
                b: Boolean
            ) {
                volume.setText("Media Volume : $newVolume")
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        ///////////////////////////////////////////////////////////////////////////////
        ////////////////////////BACK BUTTON////////////////////////////////////////////
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
 /////////////////////////////TIMER STARTINGS//////////////////////////////////////////////////
    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }
    private fun setStart() {
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }
            override fun onFinish() {
                restProgress = 0
                mediaPlayer?.start()
                setupExerciseView()
            }
        }.start()
    }
    private fun setupStart() {
        if (restTime!!.text.toString().length == 0) {
            showSnackBar("No info.")
        } else {
            llRestView.visibility = View.VISIBLE
            llExerciseView.visibility = View.GONE
            showSnackBar("Get Ready")
            if (restTimer != null) {
                restTimer!!.cancel()
                restProgress = 0
            }
            setStart()
        }
    }
    private fun setRestProgressBar() {
        val txtInput = restTime!!.text.toString()
        val timeInput = txtInput.toLong() * 1000
        timeMil = timeInput
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(
            timeMil, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = timeMil.toInt() - restProgress
                tvTimer.text = (txtInput.toInt() - restProgress).toString()
            }
            override fun onFinish() {
                mediaPlayer?.start()
                tvExerciseTimer.text = workoutTime!!.text.toString()
                setupExerciseView()
            }
        }.start()
    }
    private fun setupExerciseView() {
        quit.visibility = View.VISIBLE
        if (workoutTime!!.text.toString().length == 0) {
            showSnackBar("No info.")
        } else {
            llRestView.visibility = View.GONE
            startFrame.visibility = View.GONE
            llExerciseView.visibility = View.VISIBLE
            buttonpip.visibility = View.VISIBLE
            if (exerciseTimer != null) {
                exerciseTimer!!.cancel()
                exerciseProgress = 0
            }
            setExerciseProgressBar()
        }
    }
    private fun setupRestView() {
        if (restTime!!.text.toString().length == 0) {
            showSnackBar("No info")
        } else {
            startFrame.visibility = View.VISIBLE
            llExerciseView.visibility = View.GONE
            if (restTimer != null) {
                restTimer!!.cancel()
                restProgress = 0
            }
            setRestProgressBar()
        }
    }
    private fun setExerciseProgressBar() {
        val txtInput = workoutTime!!.text.toString()
        val timeInput = txtInput.toLong() * 1000
        timeMil = timeInput
        exerciseProgress = txtInput.toInt()
        progressBarExercise.progress = exerciseProgress
        restTimer = object : CountDownTimer(
            timeMil, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress--
                progressBarExercise.progress = timeMil.toInt() - exerciseProgress
                tvExerciseTimer.text = (txtInput.toInt() - exerciseProgress).toString()
            }
            override fun onFinish() {

                mediaPlayer?.start()
                intervalCounter++
                prefs!!.intervalPrefCount = prefs!!.intervalPrefCount!! + 1
                var interCountTextInt = intervalCountText!!.text.toString()
                var intervals = interCountTextInt.toInt()
                intervalCountExercise.text = (intervalCounter.toInt() - 1).toString()
                if (intervalCounter <= intervals) {
                    setupRestView()
                } else if (intervalCounter == intervals + 1) {
                    llRestView.visibility = View.VISIBLE
                    startButton.visibility = View.VISIBLE
                    restTime.visibility = View.VISIBLE
                    workoutTime.visibility = View.VISIBLE
                    intervalCountText.visibility = View.VISIBLE
                    llExerciseView.visibility = View.GONE
                    startFrame.visibility = View.GONE
                    intervalCounter = 1
                    restProgress = 0
                    if (intervalCounter == 1) {
                        showSnackBar("$intervals interval(s) completed!")
                        started = false
                    }
                }
            }
        }.start()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean  {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
        when (item.itemId) {
            R.id.action_save_work -> {
                if(intervalCountText.text.isNullOrEmpty() && workoutTime.text.isNullOrEmpty() && restTime.text.isNullOrEmpty()) {
                    showSnackBar("Not enough data")
                } else {
                    var restTime = restTime.text.toString()
                    var workout = workoutTime.text.toString()
                    var intervals = intervalCountText.text.toString()
                    var note =
                        Favorite(
                            0, restTime,
                            System.currentTimeMillis(),
                            workout,
                            intervals
                        )
                    viewModel.addNotes(note)
                    showSnackBar("Saved")
                }
            }
            R.id.action_get_workout -> {
                val intent = Intent(this, FavActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(started == true) {
            val intent = Intent(this, ExcerciseActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
