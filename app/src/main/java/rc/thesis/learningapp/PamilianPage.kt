package rc.thesis.learningapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PamilianPage : AppCompatActivity() {

    private val btn_madali: TextView get() = findViewById(R.id.btn_madali)
    private val btn_mahirap: TextView get() = findViewById(R.id.btn_mahirap)
    private val btn_pinakamhirap: TextView get() = findViewById(R.id.btn_pinakamhirap)
    private val imagehome: ImageView get() = findViewById(R.id.imagehome)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pamilian_page)

        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (insets.isVisible(WindowInsets.Type.systemBars())) {
                    hideSystemBars()
                }
            }
            view.onApplyWindowInsets(insets)
        }

        hideSystemBars()
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

//        val userName = intent.getStringExtra(Constants.USER_NAME)

        btn_madali.setOnClickListener {
            val intent = Intent(this@PamilianPage, Gawain_Page2::class.java)
//            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
            finish()
        }

        btn_mahirap.setOnClickListener {
            val intent = Intent(this@PamilianPage, GawainMahirap::class.java)
//            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
            finish()
        }

        btn_pinakamhirap.setOnClickListener {
            val intent = Intent(this@PamilianPage, GawainPinakaMahirap::class.java)
//            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
            finish()
        }

        imagehome.setOnClickListener {
            startActivity(Intent(this@PamilianPage, Splashscreen::class.java))
            finish()
        }
    }
}