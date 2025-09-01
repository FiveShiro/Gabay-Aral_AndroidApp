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

class Pa : Fragment() {

    private lateinit var button_next_pa: MaterialButton
    private lateinit var button_balik_pa: MaterialButton
    private lateinit var speaker_pa: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtPA: TextView
    private lateinit var txtPE: TextView
    private lateinit var txtPI: TextView
    private lateinit var txtPO: TextView
    private lateinit var txtPU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pa, container, false)

        button_next_pa = view.findViewById(R.id.button_next_pa)
        button_balik_pa = view.findViewById(R.id.button_balik_pa)

        txtPA = view.findViewById(R.id.txtPA)
        txtPE = view.findViewById(R.id.txtPE)
        txtPI = view.findViewById(R.id.txtPI)
        txtPO = view.findViewById(R.id.txtPO)
        txtPU = view.findViewById(R.id.txtPU)

        button_next_pa.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_pa2_to_ra2)
        }
        button_balik_pa.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_pa2_to_nga2)
        }

        speaker_pa = view.findViewById(R.id.speaker_pa)
        speaker_pa.setOnClickListener {
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
            Runnable { highlight(txtPA) },     // Uppercase A
            Runnable { highlight(txtPE) },     // Uppercase E
            Runnable { highlight(txtPI) },     // Uppercase I
            Runnable { highlight(txtPO) },     // Uppercase O
            Runnable { highlight(txtPU) },     // Uppercase U
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.pa)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtPA, txtPE, txtPI, txtPO, txtPU).forEach {
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