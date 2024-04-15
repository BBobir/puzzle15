package com.humbur.puzzlefifteen.screen.game

interface GameContract  {
    interface Presenter{
        fun reset()
    }
    interface View{
        fun init()
        fun loadViewAndHandleClicks()
        fun loadGame(list: List<Int>)
        fun isGameOver(): Boolean
    }

    interface Model{
        fun loadNumbers(): List<Int>
        fun getShuffledNumbers():List<Int>
    }
}