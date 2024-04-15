package com.humbur.puzzlefifteen.screen.highscores

import com.humbur.puzzlefifteen.data.local.Highscore
import java.security.PrivateKey

class HighscoresPresenter(
    private val view:HighscoresContract.View,
    private val model: HighscoresContract.Model
):HighscoresContract.Presenter {
    override fun getHighscores() {
        view.setHighscores(model.getHighscoresFromDB())
    }
}