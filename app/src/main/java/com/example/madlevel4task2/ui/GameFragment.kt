package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.R
import com.example.madlevel4task2.databinding.FragmentGameBinding
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.coroutineContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var gamesRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesRepository = GameRepository(requireContext())

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_game, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_to_history -> {
                // Navigate to Game History screen
                findNavController().navigate(R.id.action_gameFragment_to_historyFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {

        binding.btnRock.setOnClickListener {
            onAddResult(R.drawable.rock)
            binding.ivPlayer.setImageResource(R.drawable.rock)
        }

        binding.btnPaper.setOnClickListener {
            onAddResult(R.drawable.paper)
            binding.ivPlayer.setImageResource(R.drawable.paper)
        }

        binding.btnScissors.setOnClickListener {
            onAddResult(R.drawable.scissors)
             binding.ivPlayer.setImageResource(R.drawable.scissors)
        }
    }

    private fun onAddResult(chosenMove: Int) {
        var computerMove = (1..3).random()
        var result = "lose"

        when (computerMove) {
            1 -> {
                binding.ivComputer.setImageResource(R.drawable.rock)
                computerMove = R.drawable.rock
            }
            2 -> {
                binding.ivComputer.setImageResource(R.drawable.paper)
                computerMove = R.drawable.paper
            }
            3 -> {
                binding.ivComputer.setImageResource(R.drawable.scissors)
                computerMove = R.drawable.scissors
            }
        }

        if (computerMove == chosenMove) {
            result = "draw"
        }
        else if (computerMove == R.drawable.scissors && chosenMove == R.drawable.rock) {
            result = "win"
        }
        else if (computerMove == R.drawable.paper && chosenMove == R.drawable.scissors) {
            result = "win"
        }
        else if (computerMove == R.drawable.rock && chosenMove == R.drawable.paper) {
            result = "win"
        }

        binding.tvResult.text = result

        var gameResult = Game(Date(), computerMove, chosenMove, result)

        mainScope.launch {
            withContext(Dispatchers.IO) {
                gamesRepository.insertGame(gameResult)
            }
        }
    }
}