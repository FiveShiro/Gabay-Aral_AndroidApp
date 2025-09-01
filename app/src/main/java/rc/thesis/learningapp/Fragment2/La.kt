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

class La : Fragment() {

    private lateinit var button_next_la: MaterialButton
    private lateinit var button_balik_la: MaterialButton
    private lateinit var speaker_la: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtLA: TextView
    private lateinit var txtLE: TextView
    private lateinit var txtLI: TextView
    private lateinit var txtLO: TextView
    private lateinit var txtLU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_la, container, false)

        button_next_la = view.findViewById(R.id.button_next_la)
        button_balik_la = view.findViewById(R.id.button_balik_la)

        txtLA = view.findViewById(R.id.txtLA)
        txtLE = view.findViewById(R.id.txtLE)
        txtLI = view.findViewById(R.id.txtLI)
        txtLO = view.findViewById(R.id.txtLO)
        txtLU = view.findViewById(R.id.txtLU)

        button_next_la.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_la2_to_ma2)
        }
        button_balik_la.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_la2_to_ha2)
        }

        speaker_la = view.findViewById(R.id.speaker_la)
        speaker_la.setOnClickListener {
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
            Runnable { highlight(txtLA) },     // Uppercase A
            Runnable { highlight(txtLE) },     // Uppercase E
            Runnable { highlight(txtLI) },     // Uppercase I
            Runnable { highlight(txtLO) },     // Uppercase O
            Runnable { highlight(txtLU) },     // Uppercase U
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.la)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtLA, txtLE, txtLI, txtLO, txtLU).forEach {
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