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

class NnPage : Fragment() {

    private lateinit var button_next_enye: MaterialButton
    private lateinit var button_balik_enye: MaterialButton
    private lateinit var speaker_enye: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_nn_page, container, false)

        button_next_enye = view.findViewById(R.id.button_next_enye)
        button_balik_enye = view.findViewById(R.id.button_balik_enye)

        button_next_enye.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_nnPage_to_ngPage)
        }
        button_balik_enye.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_nnPage_to_NPage)
        }

        speaker_enye = view.findViewById(R.id.speaker_enye)
        speaker_enye.setOnClickListener {
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.nyesound)
            mediaPlayer.start()
        }
    }
}