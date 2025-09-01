package rc.thesis.learningapp
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GawainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gawain_page)

        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (insets.isVisible(WindowInsets.Type.systemBars())) {
                    hideSystemBars()
                }
            }
            view.onApplyWindowInsets(insets)
        }
        clickListener()
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
        val imageHome = findViewById<ImageView>(R.id.imagehome)
        val btnStart = findViewById<Button>(R.id.btn_start)

        imageHome.setOnClickListener {
            startActivity(Intent(this@GawainPage, Splashscreen::class.java))
            finish()
        }

        btnStart.setOnClickListener {
            val et_Name = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.et_name)
            if (et_Name.text.toString().isEmpty()){
                Toast.makeText(this, "Mag lagay ng pangalan", Toast.LENGTH_SHORT).show()
            }

            else {
                val intent = Intent(this, PamilianPage::class.java)
//                intent.putExtra(Constants.USER_NAME, et_Name.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}