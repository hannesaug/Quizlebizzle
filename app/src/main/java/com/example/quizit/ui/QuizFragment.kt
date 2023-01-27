package com.example.quizit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizit.QuizViewModel
import com.example.quizit.databinding.FragmentQuizBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuizFragment: Fragment() {

    private lateinit var binding: FragmentQuizBinding

//    private val viewModel: QuizViewModel by viewModels()

    private val viewModel: QuizViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.gameEnd.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToFragmentEndresult())
            }
        }


        viewModel.score.observe(viewLifecycleOwner) {
            binding.scoreText.text = it.toString()
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            binding.questionText.text = it.name
        }




        binding.musicianButton.setOnClickListener {
            viewModel.checkAnswer(true)

        }

        binding.footballButton.setOnClickListener {
            viewModel.checkAnswer(false)

        }

    }
    /*private fun updateUi() {
        binding.scoreText.text = viewModel.score.toString()
        binding.questionText.text = viewModel.currentQuestion.name

        if (viewModel.totalscore.value == 5) {
            showEndDialog()
        }
    }*/

    private fun showEndDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Well done")
            .setMessage("You are wise, You got ${viewModel.score}/${viewModel.totalscore} questions right!")
            .setCancelable(false)
            .setNegativeButton("verlassen") { _,_->
                activity?.finish()
            }
            .setPositiveButton("nochmal") { _,_ ->
                viewModel.restartGame()

            }
            .show()


    }

}