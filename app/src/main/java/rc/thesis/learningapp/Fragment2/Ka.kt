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

class Ka : Fragment() {

    private lateinit var button_next_ka: MaterialButton
    private lateinit var button_balik_ka: MaterialButton
    private lateinit var speaker_ka: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtKA: TextView
    private lateinit var txtKE: TextView
    private lateinit var txtKI: TextView
    private lateinit var txtKO: TextView
    private lateinit var txtKU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ka, container, false)

        button_next_ka = view.findViewById(R.id.button_next_ka)
        button_balik_ka = view.findViewById(R.id.button_balik_ka)

        txtKA = view.findViewById(R.id.txtKA)
        txtKE = view.findViewById(R.id.txtKE)
        txtKI = view.findViewById(R.id.txtKI)
        txtKO = view.findViewById(R.id.txtKO)
        txtKU = view.findViewById(R.id.txtKU)

        button_next_ka.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ka2_to_da2)
        }
        button_balik_ka.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_ka2_to_ba3)
        }

        speaker_ka = view.findViewById(R.id.speaker_ka)
        speaker_ka.setOnClickListener {
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
            Runnable { highlight(txtKA) },     // Uppercase A
            Runnable { highlight(txtKE) },     // Uppercase E
            Runnable { highlight(txtKI) },     // Uppercase I
            Runnable { highlight(txtKO) },     // Uppercase O
            Runnable { highlight(txtKU) },     // Uppercase U
            Runnable { resetVowels() }
        )

        val delays = listOf(500L, 1500L, 2000L, 3000L, 3500L, 4000L)

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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ka)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtKA, txtKE, txtKI, txtKO, txtKU).forEach {
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