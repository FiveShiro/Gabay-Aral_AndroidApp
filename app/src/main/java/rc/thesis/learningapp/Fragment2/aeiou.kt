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
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import rc.thesis.learningapp.R

class aeiou : Fragment() {

    private lateinit var button_next_aeiou: MaterialButton
    private lateinit var speaker_aeiou: Button
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var txtA: TextView
    private lateinit var txtE: TextView
    private lateinit var txtI: TextView
    private lateinit var txtO: TextView
    private lateinit var txtU: TextView

    private lateinit var txta: TextView
    private lateinit var txte: TextView
    private lateinit var txti: TextView
    private lateinit var txto: TextView
    private lateinit var txtu: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val runnableList = mutableListOf<Runnable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_aeiou, container, false)

        button_next_aeiou = view.findViewById(R.id.button_next_aeiou)

        txtA = view.findViewById(R.id.txtA)
        txtE = view.findViewById(R.id.txtE)
        txtI = view.findViewById(R.id.txtI)
        txtO = view.findViewById(R.id.txtO)
        txtU = view.findViewById(R.id.txtU)

        txta = view.findViewById(R.id.minitxta)
        txte = view.findViewById(R.id.minitxte)
        txti = view.findViewById(R.id.minitxti)
        txto = view.findViewById(R.id.minitxto)
        txtu = view.findViewById(R.id.minitxtu)

        button_next_aeiou.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_aeiou2_to_ba3)
        }

        speaker_aeiou = view.findViewById(R.id.speaker_aeiou)

        speaker_aeiou.setOnClickListener {
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.aeiou)
            mediaPlayer.start()
        }

        highlightAllVowels()
    }

    private fun highlightAllVowels() {
        cancelAllHighlights()
        resetHighlight()

        val allRunnables = listOf(
            Runnable { highlight(txtA) },     // Uppercase A
            Runnable { highlight(txta) },     // Lowercase a
            Runnable { highlight(txtE) },     // Uppercase E
            Runnable { highlight(txte) },     // Lowercase e
            Runnable { highlight(txtI) },     // Uppercase I
            Runnable { highlight(txti) },     // Lowercase i
            Runnable { highlight(txtO) },     // Uppercase O
            Runnable { highlight(txto) },     // Lowercase o
            Runnable { highlight(txtU) },     // Uppercase U
            Runnable { highlight(txtu) },     // Lowercase u
            Runnable { resetHighlight() }
        )

        val delays = listOf(500L, 500L, 1000L, 1000L, 1500L, 1500L, 2000L, 2000L, 2500L, 2500L, 3000L)

        allRunnables.forEachIndexed { index, runnable ->
            handler.postDelayed(runnable, delays[index])
            runnableList.add(runnable)
        }
    }

    private fun highlight(textView: TextView) {
        textView.setTypeface(null, Typeface.BOLD)
        textView.setTextColor(Color.RED)
    }

    private fun resetCapitalVowels() {
        listOf(txtA, txtE, txtI, txtO, txtU).forEach {
            it.setTypeface(null, Typeface.NORMAL)
            it.setTextColor(Color.BLACK) // or your original color
        }
    }

    private fun resetMiniVowels() {
        listOf(txta, txte, txti, txto, txtu).forEach {
            it.setTypeface(null, Typeface.NORMAL)
            it.setTextColor(Color.BLACK)
        }
    }

    private fun resetHighlight() {
        // Reset both sets if needed
        resetCapitalVowels()
        resetMiniVowels()
    }

    private fun cancelAllHighlights() {
        for (runnable in runnableList) {
            handler.removeCallbacks(runnable)
        }
        runnableList.clear()
    }
}