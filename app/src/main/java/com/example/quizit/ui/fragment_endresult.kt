package com.example.quizit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizit.QuizViewModel
import com.example.quizit.databinding.EndresultFragmentBinding

class fragment_endresult: Fragment() {

    private lateinit var binding: EndresultFragmentBinding
//   /* private val viewModel: QuizViewModel by viewModels()*/

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EndresultFragmentBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.score.observe(viewLifecycleOwner) {
            binding.resultScoreText.text = "Well done!\n You have $it Points"
        }
        binding.resultAgainButton.setOnClickListener {
            viewModel.restartGame()
            findNavController().navigateUp()
        }
    }
}