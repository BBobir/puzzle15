package com.humbur.puzzlefifteen.screen.highscores

import com.humbur.puzzlefifteen.data.local.Highscore

interface HighscoresContract {
    interface Presenter{
        fun getHighscores()
    }
    interface View{
        fun setHighscores(list: List<Highscore>)
    }
    interface Model{
        fun getHighscoresFromDB():List<Highscore>
    }
}