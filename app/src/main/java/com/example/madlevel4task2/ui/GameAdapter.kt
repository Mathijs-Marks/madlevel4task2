package com.example.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.databinding.ItemGameBinding
import com.example.madlevel4task2.model.Game

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemGameBinding.bind(itemView)

        fun dataBind(game: Game) {
            binding.tvHistoryResult.text = game.gameResult
            binding.tvHistoryDate.text = game.date.toString()
            binding.ivHistoryComputer.setImageResource(game.computerMove)
            binding.ivHistoryPlayer.setImageResource(game.playerMove)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(games[position])
    }
}