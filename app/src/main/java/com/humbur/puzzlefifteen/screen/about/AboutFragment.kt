package com.humbur.puzzlefifteen.screen.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.humbur.puzzlefifteen.R
import com.humbur.puzzlefifteen.databinding.FragmentAboutBinding
import com.humbur.puzzlefifteen.screen.about.model.About


class AboutFragment : Fragment(R.layout.fragment_about),AboutContract.View {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: AboutContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutBinding.bind(view)

        presenter=AboutPresenter(this,AboutModul(requireContext()))
        presenter.showAbout()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setAbout(about: About) {
        with(binding){
            ivIcon.setImageResource(about.appIcon)
            txtAppName.text = about.title
            txtInstruction.text = about.instruction
            txtDevName.text = about.developer
            txtDevEmail.text = about.email
        }
    }
}