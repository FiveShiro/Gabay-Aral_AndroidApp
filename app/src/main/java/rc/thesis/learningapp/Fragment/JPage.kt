package rc.thesis.learningapp.Fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import rc.thesis.learningapp.R

class JPage : Fragment() {

    private lateinit var button_next_jey: MaterialButton
    private lateinit var button_balik_jey: MaterialButton
    private lateinit var speaker_jey: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_j_page, container, false)

        button_next_jey = view.findViewById(R.id.button_next_jey)
        button_balik_jey = view.findViewById(R.id.button_balik_jey)

        button_next_jey.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_JPage_to_KPage)
        }
        button_balik_jey.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_JPage_to_IPage)
        }

        speaker_jey = view.findViewById(R.id.speaker_jey)
        speaker_jey.setOnClickListener {
            playAudio()
        }

        return view
    }

    //reminder: function for closing activity, calls stopAudio.. bug fix
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
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.jsound)
            mediaPlayer.start()
        }
    }
}