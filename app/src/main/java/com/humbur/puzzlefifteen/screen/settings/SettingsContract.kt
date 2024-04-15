package com.humbur.puzzlefifteen.screen.settings

interface SettingsContract {
    interface Presenter{
        fun clearAllHighscores()
        fun getLastMusicFxState()
        fun getLastSoundFxState()
        fun setMusicFxState()
        fun setMusicFxState(state:Boolean)
        fun setSoundFxState(state:Boolean)

    }
    interface View{
        fun setMusicSwitchState(state:Boolean)
        fun setSoundSwitchState(state:Boolean)
    }
    interface Model{
        fun getMusicFxState():Boolean
        fun getSoundFxState():Boolean
        fun saveMusicFxState(state: Boolean)
        fun saveSoundFxState(state:Boolean)
        fun deleteAllHighscoresFromDB()
    }
}