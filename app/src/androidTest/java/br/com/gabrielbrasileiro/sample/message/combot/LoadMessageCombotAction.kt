package br.com.gabrielbrasileiro.sample.message.combot

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.gabrielbrasileiro.combot.core.CombotAction

class LoadMessageCombotAction : CombotAction() {

    fun clickGetNewMessage() {
        onNodeWithText("Load new message").performClick()
    }

}