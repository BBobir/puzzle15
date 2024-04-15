package com.humbur.puzzlefifteen.screen.gameover

class GameOverPresenter(
    private val model:GameOverContract.Model,
    private val view:GameOverContract.View
):GameOverContract.Presneter{
    override fun startAnimation() {
       view.setPartyAnimation(model.getPartyAnimation())
    }

    override fun saveHighscore() {
        view.setHighscore(model.getDatabaseInstance())
    }


}