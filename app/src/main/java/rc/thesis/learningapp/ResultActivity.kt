package rc.thesis.learningapp
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class ResultActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val tv_score: TextView = findViewById(R.id.tv_score)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tv_score.text = "Ang iyong puntos ay $correctAnswers/$totalQuestions"

        val btn_finish: Button = findViewById(R.id.btn_finish)

        btn_finish.setOnClickListener {
            val intent = Intent(this, PamilianPage::class.java)
            startActivity(intent)
            finish()
        }

        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (insets.isVisible(WindowInsets.Type.systemBars())) {
                    hideSystemBars()
                }
            }
            view.onApplyWindowInsets(insets)
        }

        hideSystemBars()

        val konfettiView = findViewById<KonfettiView>(R.id.konfettiView)

        // Start Confetti Animation
        konfettiView.start(
            listOf(
                nl.dionsegijn.konfetti.core.Party(
                    speed = 0f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(0xfce18a, 0xff726d, 0xb48def, 0xf4306d),
                    emitter = Emitter(duration = 3, TimeUnit.SECONDS).perSecond(300),
                    position = Position.Relative(0.5, 0.3)
                )
            )
        )

        mediaPlayer = MediaPlayer.create(this, R.raw.resultcheer)
        mediaPlayer.start()
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

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}