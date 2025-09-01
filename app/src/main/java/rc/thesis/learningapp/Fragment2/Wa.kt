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

class Wa : Fragment() {

    private lateinit var button_next_wa: MaterialButton
    private lateinit var button_balik_wa: MaterialButton
    private lateinit var speaker_wa: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtWA: TextView
    private lateinit var txtWE: TextView
    private lateinit var txtWI: TextView
    private lateinit var txtWO: TextView
    private lateinit var txtWU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_wa, container, false)

        button_next_wa = view.findViewById(R.id.button_next_wa)
        button_balik_wa = view.findViewById(R.id.button_balik_wa)

        txtWA = view.findViewById(R.id.txtWA)
        txtWE = view.findViewById(R.id.txtWE)
        txtWI = view.findViewById(R.id.txtWI)
        txtWO = view.findViewById(R.id.txtWO)
        txtWU = view.findViewById(R.id.txtWU)


        button_next_wa.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_wa2_to_ya2)
        }
        button_balik_wa.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_wa2_to_ta2)
        }

        speaker_wa = view.findViewById(R.id.speaker_wa)
        speaker_wa.setOnClickListener {
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
            Runnable { highlight(txtWA) },     // Uppercase A
            Runnable { highlight(txtWE) },     // Uppercase E
            Runnable { highlight(txtWI) },     // Uppercase I
            Runnable { highlight(txtWO) },     // Uppercase O
            Runnable { highlight(txtWU) },     // Uppercase U
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.wa)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtWA, txtWE, txtWI, txtWO, txtWU).forEach {
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