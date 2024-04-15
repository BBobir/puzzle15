package com.humbur.puzzlefifteen.screen.game

class GamePresenter (
    private val model: GameContract.Model,
    private val view: GameContract.View
):GameContract.Presenter{

    init {
        model.loadNumbers()
        view.init()
        view.loadViewAndHandleClicks()
        view.loadGame(model.getShuffledNumbers())
    }
    override fun reset() {
       view.loadGame(model.getShuffledNumbers())
    }
}