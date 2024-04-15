package com.humbur.puzzlefifteen.screen.gameover

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.humbur.puzzlefifteen.R
import com.humbur.puzzlefifteen.data.local.Highscore
import com.humbur.puzzlefifteen.data.local.HighscoresDatabase
import com.humbur.puzzlefifteen.databinding.FragmentGameOverBinding
import nl.dionsegijn.konfetti.core.Party


class GameOverFragment : Fragment(R.layout.fragment_game_over), GameOverContract.View {
    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: GameOverContract.Presneter

    private val args: GameOverFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameOverBinding.bind(view)

        presenter = GameOverPresenter(GameOverModel(requireContext()), this)
        presenter.saveHighscore()
        presenter.startAnimation()
        showWinnerText()

        binding.btnRestart.setOnClickListener{
            findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
        }
        binding.btnMainMenu.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.btnExit.setOnClickListener{

            requireActivity().finish()
        }
    }

    private fun showWinnerText() {
        binding.txtGameOverInfo.text = "Congratulations! You won the game with ${args.moves} moves"
    }

    override fun setPartyAnimation(party: Party) {
        binding.konfettiView.start(party)
    }

    override fun setHighscore(db: HighscoresDatabase) {
        Log.d("NEW_TAG", "setHighscore: ${args.moves}")
        val highscore = Highscore(moves=args.moves)
        db.insert(highscore)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}