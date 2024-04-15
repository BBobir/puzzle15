package com.humbur.puzzlefifteen.data.local

import android.os.Build.VERSION

object DatabaseConfig {
    const val DATABASE_NAME = "highscores"
    const val VERSION = 1
    const val TABLE_NAME = "highscores_table"

    const val COL_ID = "id"
    const val COL_MOVES = "moves"
}