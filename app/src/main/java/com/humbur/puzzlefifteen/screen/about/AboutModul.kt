package com.humbur.puzzlefifteen.screen.about

import android.content.Context
import com.humbur.puzzlefifteen.R
import com.humbur.puzzlefifteen.screen.about.model.About

class AboutModul(private val context: Context):AboutContract.Model {
    override fun getAbout(): About {
        return About(
            appIcon = R.drawable.puzzle15,
            title = context.getString(R.string.app_name),
            instruction = context.getString(R.string.Instruction),
            developer = context.getString(R.string.developer_name),
            email = context.getString(R.string.developer_email)
        )
    }
}