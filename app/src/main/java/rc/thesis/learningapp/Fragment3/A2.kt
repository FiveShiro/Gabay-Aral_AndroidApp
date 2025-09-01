package rc.thesis.learningapp.Fragment3

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import rc.thesis.learningapp.R

class A2 : Fragment(){

    private lateinit var button_next_a2: MaterialButton
    private lateinit var button_balik_a2: MaterialButton
    private lateinit var speaker_a2: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_a2, container, false)

        button_next_a2 = view.findViewById(R.id.button_next_a2)
        button_balik_a2 = view.findViewById(R.id.button_balik_a2)

        button_next_a2.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_a22_to_a32)
        }
        button_balik_a2.setOnClickListener {
            stopAudio()
            Navigation.findNavController(view).navigate(R.id.action_a22_to_a12)
        }

        speaker_a2 = view.findViewById(R.id.speaker_a2)
        speaker_a2.setOnClickListener {
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
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
        } else {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.a2audio)
            mediaPlayer.start()
        }
    }
}