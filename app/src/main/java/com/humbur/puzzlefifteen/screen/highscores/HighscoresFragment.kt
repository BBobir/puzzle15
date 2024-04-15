package com.humbur.puzzlefifteen.screen.highscores


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.humbur.puzzlefifteen.R
import com.humbur.puzzlefifteen.data.local.Highscore
import com.humbur.puzzlefifteen.databinding.FragmentHighScoresBinding
import com.humbur.puzzlefifteen.screen.highscores.adapter.HighscoresAdapter


class HighscoresFragment : Fragment(R.layout.fragment_high_scores),HighscoresContract.View {
    private var _binding: FragmentHighScoresBinding? = null
    private val binding get() = _binding!!

    private var highscoresAdapter: HighscoresAdapter? = null
    private lateinit var presenter: HighscoresPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHighScoresBinding.bind(view)


        presenter = HighscoresPresenter(this, HighscoresModel(requireContext()))
        presenter.getHighscores()

        binding.ivBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setHighscores(list: List<Highscore>) {
        highscoresAdapter = HighscoresAdapter(list)
        binding.rvHighscores.adapter=highscoresAdapter
    }
}