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

class Da : Fragment() {

    private lateinit var button_next_da: MaterialButton
    private lateinit var button_balik_da: MaterialButton
    private lateinit var speaker_da: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtDA: TextView
    private lateinit var txtDE: TextView
    private lateinit var txtDI: TextView
    private lateinit var txtDO: TextView
    private lateinit var txtDU: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_da, container, false)

        button_next_da = view.findViewById(R.id.button_next_da)
        button_balik_da = view.findViewById(R.id.button_balik_da)

        txtDA = view.findViewById(R.id.txtDA)
        txtDE = view.findViewById(R.id.txtDE)
        txtDI = view.findViewById(R.id.txtDI)
        txtDO = view.findViewById(R.id.txtDO)
        txtDU = view.findViewById(R.id.txtDU)

        button_next_da.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_da2_to_ga2)
        }
        button_balik_da.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_da2_to_ka2)
        }

        speaker_da = view.findViewById(R.id.speaker_da)
        speaker_da.setOnClickListener {
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
    //reminder: function for disabling audio duplication.. bug fix womp womp
    private fun playAudio(){
        cancelAllHighlights()

        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.da)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlightAllVowels() {
        cancelAllHighlights()
        resetVowels()

        val allRunnables = listOf(
            Runnable { highlight(txtDA) },     // Uppercase A
            Runnable { highlight(txtDE) },     // Uppercase E
            Runnable { highlight(txtDI) },     // Uppercase I
            Runnable { highlight(txtDO) },     // Uppercase O
            Runnable { highlight(txtDU) },     // Uppercase U
            Runnable { resetVowels() }
        )

        val delays = listOf(500L, 1000L, 1500L, 2500L, 3000L, 3500L)

        allRunnables.forEachIndexed { index, runnable ->
            handler.postDelayed(runnable, delays[index])
            runnableList.add(runnable)
        }
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetVowels() {
        listOf(txtDA, txtDE, txtDI, txtDO, txtDU).forEach {
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