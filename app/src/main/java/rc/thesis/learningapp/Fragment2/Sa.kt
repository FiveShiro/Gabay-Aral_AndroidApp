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

class Sa : Fragment() {

    private lateinit var button_next_sa: MaterialButton
    private lateinit var button_balik_sa: MaterialButton
    private lateinit var speaker_sa: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtSA: TextView
    private lateinit var txtSE: TextView
    private lateinit var txtSI: TextView
    private lateinit var txtSO: TextView
    private lateinit var txtSU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_sa, container, false)

        button_next_sa = view.findViewById(R.id.button_next_sa)
        button_balik_sa = view.findViewById(R.id.button_balik_sa)

        txtSA = view.findViewById(R.id.txtSA)
        txtSE = view.findViewById(R.id.txtSE)
        txtSI = view.findViewById(R.id.txtSI)
        txtSO = view.findViewById(R.id.txtSO)
        txtSU = view.findViewById(R.id.txtSU)

        button_next_sa.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_sa2_to_ta2)
        }
        button_balik_sa.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_sa2_to_ra2)
        }

        speaker_sa = view.findViewById(R.id.speaker_sa)
        speaker_sa.setOnClickListener {
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
            Runnable { highlight(txtSA) },     // Uppercase A
            Runnable { highlight(txtSE) },     // Uppercase E
            Runnable { highlight(txtSI) },     // Uppercase I
            Runnable { highlight(txtSO) },     // Uppercase O
            Runnable { highlight(txtSU) },     // Uppercase U
            Runnable { resetVowels() }
        )

        val delays = listOf(500L, 1000L, 1500L, 2000L, 2500L, 3000L)

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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sa)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtSA, txtSE, txtSI, txtSO, txtSU).forEach {
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