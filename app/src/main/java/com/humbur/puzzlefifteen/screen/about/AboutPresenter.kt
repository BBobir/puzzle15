package com.humbur.puzzlefifteen.screen.about

import java.security.PrivateKey

class AboutPresenter(
    private val view:AboutContract.View,
    private val model:AboutContract.Model
):AboutContract.Presenter {
    override fun showAbout() {
        view.setAbout(model.getAbout())
    }
}