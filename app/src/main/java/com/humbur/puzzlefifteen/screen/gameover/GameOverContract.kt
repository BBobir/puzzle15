package com.humbur.puzzlefifteen.screen.gameover

import com.humbur.puzzlefifteen.data.local.HighscoresDatabase
import nl.dionsegijn.konfetti.core.Party

interface GameOverContract {
    interface Presneter{
        fun startAnimation()
        fun saveHighscore()
    }
    interface View{
        fun setPartyAnimation(party: Party)
        fun setHighscore(db:HighscoresDatabase)
    }
    interface Model{
        fun getPartyAnimation(): Party
        fun getDatabaseInstance():HighscoresDatabase
    }

}