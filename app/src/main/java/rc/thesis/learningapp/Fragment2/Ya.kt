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

class Ya : Fragment() {

    private lateinit var button_balik_ya: MaterialButton
    private lateinit var speaker_ya: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtYA: TextView
    private lateinit var txtYE: TextView
    private lateinit var txtYI: TextView
    private lateinit var txtYO: TextView
    private lateinit var txtYU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ya, container, false)

        button_balik_ya = view.findViewById(R.id.button_balik_ya)

        txtYA = view.findViewById(R.id.txtYA)
        txtYE = view.findViewById(R.id.txtYE)
        txtYI = view.findViewById(R.id.txtYI)
        txtYO = view.findViewById(R.id.txtYO)
        txtYU = view.findViewById(R.id.txtYU)

        button_balik_ya.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ya2_to_wa2)
        }

        speaker_ya = view.findViewById(R.id.speaker_ya)
        speaker_ya.setOnClickListener {
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
            Runnable { highlight(txtYA) },     // Uppercase A
            Runnable { highlight(txtYE) },     // Uppercase E
            Runnable { highlight(txtYI) },     // Uppercase I
            Runnable { highlight(txtYO) },     // Uppercase O
            Runnable { highlight(txtYU) },     // Uppercase U
            Runnable { resetVowels() }
        )

        val delays = listOf(500L, 1000L, 2000L, 2500L, 3000L, 3500L)

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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ya)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtYA, txtYE, txtYI, txtYO, txtYU).forEach {
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