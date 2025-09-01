package rc.thesis.learningapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GawainPinakaMahirap : AppCompatActivity(), OnClickListener {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btnMusicToggle: ImageButton
    private var isPlaying = true

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0
//    private var mUserName: String? = null

    private val tv_option_one: TextView get() = findViewById(R.id.tv_option_one)
    private val tv_option_two: TextView get() = findViewById(R.id.tv_option_two)
    private val tv_option_three: TextView get() = findViewById(R.id.tv_option_three)
    private val tv_option_four: TextView get() = findViewById(R.id.tv_option_four)
    private val tv_option_five: TextView get() = findViewById(R.id.tv_option_five)
    private val btn_submit: Button get() = findViewById(R.id.btn_submit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gawain_page2)

        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (insets.isVisible(WindowInsets.Type.systemBars())) {
                    hideSystemBars()
                }
            }
            view.onApplyWindowInsets(insets)
        }

        hideSystemBars()

        btnMusicToggle = findViewById(R.id.musicBtn)
        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic2)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

//        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions3()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        tv_option_five.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

        setQuestion()
        clickListener()
    }

    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    private fun clickListener() {
        val imageHome = findViewById<ImageView>(R.id.imagehome)
//        val userName = intent.getStringExtra(Constants.USER_NAME)

        imageHome.setOnClickListener {
            val intent = Intent(this@GawainPinakaMahirap, PamilianPage::class.java)
//            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
            finish()
        }

        btnMusicToggle.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                btnMusicToggle.setImageResource(R.drawable.musicoff)
            } else {
                mediaPlayer.start()
                btnMusicToggle.setImageResource(R.drawable.musicon)
            }
            isPlaying = !isPlaying
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){

        val question = mQuestionsList?.take(10)!![mCurrentPosition-1]
        defaultOptionsView()

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = mCurrentPosition

        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = buildString {
            append(mCurrentPosition)
            append("/")
            append(progressBar.max)
        }

        val iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(question.image)

        tv_option_one.text = question.optionOne

        tv_option_two.text = question.optionTwo

        tv_option_three.text = question.optionThree

        tv_option_four.text = question.optionFour

        tv_option_five.text = question.optionFive
    }

    private fun defaultOptionsView(){

        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)
        options.add(4, tv_option_five)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> selectedOptionView(tv_option_one, 1)
            R.id.tv_option_two -> selectedOptionView(tv_option_two, 2)
            R.id.tv_option_three -> selectedOptionView(tv_option_three, 3)
            R.id.tv_option_four -> selectedOptionView(tv_option_four, 4)
            R.id.tv_option_five -> selectedOptionView(tv_option_five, 5)
            R.id.btn_submit -> {

                if (checkIfOptionSelected()) {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    btn_submit.visibility = View.INVISIBLE

                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        playSound(R.raw.wrongsound)
                    } else {
                        mCorrectAnswer++
                        playSound(R.raw.hooray)
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    mCurrentPosition++
                    when {
                        mCurrentPosition <= 10 -> {

                            btn_submit.postDelayed({
                                setQuestion()
                                mSelectedOptionPosition = 0
                                defaultOptionsView()

                                btn_submit.visibility = View.VISIBLE

                                btn_submit.text = if (mCurrentPosition == 10) "WAKAS" else "MAGPASA"

                            }, 2000) // 2-second delay

                        } else -> {
                            btn_submit.postDelayed({
                                val intent = Intent(this, ResultActivity::class.java)
                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, 10)
                                startActivity(intent)
                                finish()
                            }, 2000) // 2-second delay
                        }
                    }
                }
            } // end of btn_submit
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
            5 -> {
                tv_option_five.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun checkIfOptionSelected(): Boolean {
        return if (mSelectedOptionPosition == 0) {
            Toast.makeText(this, "Pumili ng sagot", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private var mediaPlayer2: MediaPlayer? = null

    private fun playSound(soundResId: Int) {
        mediaPlayer2?.release() // Release any existing media player
        mediaPlayer2 = MediaPlayer.create(this, soundResId)
        mediaPlayer2?.start()
        mediaPlayer2?.setOnCompletionListener { mp ->
            mp.release()
            mediaPlayer2 = null // Prevent memory leaks
        }
    }


    //to prevent bgmusic from playing when app is running on background
    override fun onPause() {
        super.onPause()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    //resume bgmusic on foreground
    override fun onResume() {
        super.onResume()
        if (isPlaying) {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}
