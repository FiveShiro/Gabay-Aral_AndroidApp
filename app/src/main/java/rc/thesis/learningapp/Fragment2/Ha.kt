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

class Ha : Fragment() {

    private lateinit var button_next_ha: MaterialButton
    private lateinit var button_balik_ha: MaterialButton
    private lateinit var speaker_ha: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtHA: TextView
    private lateinit var txtHE: TextView
    private lateinit var txtHI: TextView
    private lateinit var txtHO: TextView
    private lateinit var txtHU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ha, container, false)

        button_next_ha = view.findViewById(R.id.button_next_ha)
        button_balik_ha = view.findViewById(R.id.button_balik_ha)

        txtHA = view.findViewById(R.id.txtHA)
        txtHE = view.findViewById(R.id.txtHE)
        txtHI = view.findViewById(R.id.txtHI)
        txtHO = view.findViewById(R.id.txtHO)
        txtHU = view.findViewById(R.id.txtHU)

        button_next_ha.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ha2_to_la2)
        }
        button_balik_ha.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ha2_to_ga2)
        }

        speaker_ha = view.findViewById(R.id.speaker_ha)
        speaker_ha.setOnClickListener {
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
            Runnable { highlight(txtHA) },     // Uppercase A
            Runnable { highlight(txtHE) },     // Uppercase E
            Runnable { highlight(txtHI) },     // Uppercase I
            Runnable { highlight(txtHO) },     // Uppercase O
            Runnable { highlight(txtHU) },     // Uppercase U
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ha)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtHA, txtHE, txtHI, txtHO, txtHU).forEach {
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