package rc.thesis.learningapp
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class Splashscreen : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btnMusicToggle: ImageButton
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splashscreen)

        btnMusicToggle = findViewById(R.id.musicBtn)
        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        clickListener()

        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (insets.isVisible(WindowInsets.Type.systemBars())) {
                    hideSystemBars()
                }
            }
            view.onApplyWindowInsets(insets)
        }

        hideSystemBars()

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


   private fun clickListener(){
       val imageAlpabeto = findViewById<ImageView>(R.id.ivAlpabeto)
       val imageAbakada = findViewById<ImageView>(R.id.ivAbakada)
       val imageBumasa = findViewById<ImageView>(R.id.ivBumasa)
       val imageGawain = findViewById<ImageView>(R.id.ivGawain)

       imageAlpabeto.setOnClickListener {
           startActivity(Intent(this@Splashscreen, AlpabetoPage::class.java))
           finish()
       }

       imageAbakada.setOnClickListener {
           startActivity(Intent(this@Splashscreen, AbakadaPage::class.java))
           finish()
       }

       imageBumasa.setOnClickListener {
           startActivity(Intent(this@Splashscreen, PagbasaPage::class.java))
           finish()
       }

       imageGawain.setOnClickListener {
           startActivity(Intent(this@Splashscreen, PamilianPage::class.java))
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


