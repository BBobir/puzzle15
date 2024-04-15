package com.humbur.puzzlefifteen.screen.highscores

import android.content.Context
import com.humbur.puzzlefifteen.data.local.Highscore
import com.humbur.puzzlefifteen.data.local.HighscoresDatabase

class HighscoresModel(private val context:Context):HighscoresContract.Model {

    private val database:HighscoresDatabase = HighscoresDatabase(context)
    private val list:MutableList<Highscore> = ArrayList()

    override fun getHighscoresFromDB(): List<Highscore> {
        list.addAll(database.getHighscores())
        return list
    }
}