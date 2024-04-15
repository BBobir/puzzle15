package com.humbur.puzzlefifteen.screen.highscores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.humbur.puzzlefifteen.data.local.Highscore
import com.humbur.puzzlefifteen.databinding.HighscoreItemBinding

class HighscoresAdapter(private val list: List<Highscore>) :RecyclerView.Adapter<HighscoresAdapter.ViewHolder>()  {

    inner class ViewHolder(private val binding: HighscoreItemBinding):RecyclerView.ViewHolder(binding.root){
    fun onBind(highscore: Highscore){
        binding.txtRank.text = "${adapterPosition + 1} ."
        binding.txtMoves.text = "${highscore.moves} moves"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = HighscoreItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(list[position])
    }

}