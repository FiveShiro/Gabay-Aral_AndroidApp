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

class Ba : Fragment() {

    private lateinit var button_next_ba: MaterialButton
    private lateinit var button_balik_ba: MaterialButton
    private lateinit var speaker_ba: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtBA: TextView
    private lateinit var txtBE: TextView
    private lateinit var txtBI: TextView
    private lateinit var txtBO: TextView
    private lateinit var txtBU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ba, container, false)

        button_next_ba = view.findViewById(R.id.button_next_ba)
        button_balik_ba = view.findViewById(R.id.button_balik_ba)

        txtBA = view.findViewById(R.id.txtBA)
        txtBE = view.findViewById(R.id.txtBE)
        txtBI = view.findViewById(R.id.txtBI)
        txtBO = view.findViewById(R.id.txtBO)
        txtBU = view.findViewById(R.id.txtBU)

        button_next_ba.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ba3_to_ka2)
        }
        button_balik_ba.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ba3_to_aeiou2)
        }

        speaker_ba = view.findViewById(R.id.speaker_ba)
        speaker_ba.setOnClickListener {
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
            Runnable { highlight(txtBA) },     // Uppercase A
            Runnable { highlight(txtBE) },     // Uppercase E
            Runnable { highlight(txtBI) },     // Uppercase I
            Runnable { highlight(txtBO) },     // Uppercase O
            Runnable { highlight(txtBU) },     // Uppercase U
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ba)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtBA, txtBE, txtBI, txtBO, txtBU).forEach {
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