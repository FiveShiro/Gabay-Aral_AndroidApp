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

class Ga : Fragment() {

    private lateinit var button_next_ga: MaterialButton
    private lateinit var button_balik_ga: MaterialButton
    private lateinit var speaker_ga: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtGA: TextView
    private lateinit var txtGE: TextView
    private lateinit var txtGI: TextView
    private lateinit var txtGO: TextView
    private lateinit var txtGU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ga, container, false)

        button_next_ga = view.findViewById(R.id.button_next_ga)
        button_balik_ga = view.findViewById(R.id.button_balik_ga)

        txtGA = view.findViewById(R.id.txtGA)
        txtGE = view.findViewById(R.id.txtGE)
        txtGI = view.findViewById(R.id.txtGI)
        txtGO = view.findViewById(R.id.txtGO)
        txtGU = view.findViewById(R.id.txtGU)

        button_next_ga.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ga2_to_ha2)
        }
        button_balik_ga.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ga2_to_da2)
        }

        speaker_ga = view.findViewById(R.id.speaker_ga)
        speaker_ga.setOnClickListener {
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
            Runnable { highlight(txtGA) },     // Uppercase A
            Runnable { highlight(txtGE) },     // Uppercase E
            Runnable { highlight(txtGI) },     // Uppercase I
            Runnable { highlight(txtGO) },     // Uppercase O
            Runnable { highlight(txtGU) },     // Uppercase U
            Runnable { resetVowels() }
        )

        val delays = listOf(500L, 1000L, 2000L, 2500L, 3500L, 4000L)

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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ga)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtGA, txtGE, txtGI, txtGO, txtGU).forEach {
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