package com.humbur.puzzlefifteen.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.humbur.puzzlefifteen.R
import com.humbur.puzzlefifteen.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        
        binding.btnPlay.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_gameFragment)
        }

        binding.btnHighscores.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_highScoresFragment)
        }

        binding.btnSettings.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_settingsFragment)
        }
        binding.btnAbout.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_aboutFragment)
        }
        binding.btnExit.setOnClickListener { requireActivity().finish() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}