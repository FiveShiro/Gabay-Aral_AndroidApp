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

class VPage : Fragment() {

    private lateinit var button_next_vi: MaterialButton
    private lateinit var button_balik_vi: MaterialButton
    private lateinit var speaker_vi: MaterialButton
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_v_page, container, false)

        button_next_vi = view.findViewById(R.id.button_next_vi)
        button_balik_vi = view.findViewById(R.id.button_balik_vi)

        button_next_vi.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_VPage_to_WPage)
        }
        button_balik_vi.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_VPage_to_UPage)
        }

        speaker_vi = view.findViewById(R.id.speaker_vi)
        speaker_vi.setOnClickListener {
            playAudio()
        }

        return view;
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
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.vsound)
            mediaPlayer.start()
        }
    }
}