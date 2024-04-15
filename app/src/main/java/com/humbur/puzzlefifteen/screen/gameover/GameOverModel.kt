package com.humbur.puzzlefifteen.screen.gameover

import android.content.Context
import com.humbur.puzzlefifteen.data.local.HighscoresDatabase
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class GameOverModel(private val context: Context) : GameOverContract.Model {
    override fun getPartyAnimation(): Party {
        return Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
    }

    override fun getDatabaseInstance(): HighscoresDatabase {
        return HighscoresDatabase(context)
    }

}