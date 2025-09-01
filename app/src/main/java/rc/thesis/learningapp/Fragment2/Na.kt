package rc.thesis.learningapp.Fragment2

import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import rc.thesis.learningapp.R

class Na : Fragment() {

    private lateinit var button_next_na: MaterialButton
    private lateinit var button_balik_na: MaterialButton
    private lateinit var speaker_na: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtNA: TextView
    private lateinit var txtNE: TextView
    private lateinit var txtNI: TextView
    private lateinit var txtNO: TextView
    private lateinit var txtNU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_na, container, false)

        button_next_na = view.findViewById(R.id.button_next_na)
        button_balik_na = view.findViewById(R.id.button_balik_na)

        txtNA = view.findViewById(R.id.txtNA)
        txtNE = view.findViewById(R.id.txtNE)
        txtNI = view.findViewById(R.id.txtNI)
        txtNO = view.findViewById(R.id.txtNO)
        txtNU = view.findViewById(R.id.txtNU)

        button_next_na.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_na2_to_nga2)
        }
        button_balik_na.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_na2_to_ma2)
        }

        speaker_na = view.findViewById(R.id.speaker_na)
        speaker_na.setOnClickListener {
            playAudio()
        }

        return view
    }

    //reminder: function for closing pagbasa.. bug fix
    override fun onPause() {
        super.onPause()
        stopAudio()
    }

    // reminder: function for next button audio stop.. bug fix
    private fun stopAudio(){
        if (this::mediaPlayer.isInitialized) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()

            mediaPlayer = MediaPlayer()
        }
    }

    private fun highlightAllVowels() {
        cancelAllHighlights()
        resetVowels()

        val allRunnables = listOf(
            Runnable { highlight(txtNA) },     // Uppercase A
            Runnable { highlight(txtNE) },     // Uppercase E
            Runnable { highlight(txtNI) },     // Uppercase I
            Runnable { highlight(txtNO) },     // Uppercase O
            Runnable { highlight(txtNU) },     // Uppercase U
            Runnable { resetVowels() }
        )

        val delays = listOf(500L, 1000L, 1500L, 2500L, 3000L, 3500L)

        allRunnables.forEachIndexed { index, runnable ->
            handler.postDelayed(runnable, delays[index])
            runnableList.add(runnable)
        }
    }

    //reminder: function for disabling audio duplication.. bug fix womp womp
    private fun playAudio(){
        cancelAllHighlights()

        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.na)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtNA, txtNE, txtNI, txtNO, txtNU).forEach {
            it.setTypeface(null, Typeface.NORMAL)
            it.setTextColor(Color.BLACK) // or your original color
        }
    }

    private fun cancelAllHighlights() {
        for (runnable in runnableList) {
            handler.removeCallbacks(runnable)
        }
        runnableList.clear()
    }
}