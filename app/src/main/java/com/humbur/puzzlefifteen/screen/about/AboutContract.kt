package com.humbur.puzzlefifteen.screen.about

import com.humbur.puzzlefifteen.screen.about.model.About

interface AboutContract {
    interface Presenter{
        fun showAbout()
    }
    interface View{
        fun setAbout(about: About)
    }
    interface Model{
        fun getAbout():About
    }
}