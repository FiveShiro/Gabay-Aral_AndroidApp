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

class Nga : Fragment() {

    private lateinit var button_next_nga: MaterialButton
    private lateinit var button_balik_nga: MaterialButton
    private lateinit var speaker_nga: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtNGA: TextView
    private lateinit var txtNGE: TextView
    private lateinit var txtNGI: TextView
    private lateinit var txtNGO: TextView
    private lateinit var txtNGU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_nga, container, false)

        button_next_nga = view.findViewById(R.id.button_next_nga)
        button_balik_nga = view.findViewById(R.id.button_balik_nga)

        txtNGA = view.findViewById(R.id.txtNGA)
        txtNGE = view.findViewById(R.id.txtNGE)
        txtNGI = view.findViewById(R.id.txtNGI)
        txtNGO = view.findViewById(R.id.txtNGO)
        txtNGU = view.findViewById(R.id.txtNGU)


        button_next_nga.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_nga2_to_pa2)
        }
        button_balik_nga.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_nga2_to_na2)
        }

        speaker_nga = view.findViewById(R.id.speaker_nga)
        speaker_nga.setOnClickListener {
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
            Runnable { highlight(txtNGA) },     // Uppercase A
            Runnable { highlight(txtNGE) },     // Uppercase E
            Runnable { highlight(txtNGI) },     // Uppercase I
            Runnable { highlight(txtNGO) },     // Uppercase O
            Runnable { highlight(txtNGU) },     // Uppercase U
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.nga)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtNGA, txtNGE, txtNGI, txtNGO, txtNGU).forEach {
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