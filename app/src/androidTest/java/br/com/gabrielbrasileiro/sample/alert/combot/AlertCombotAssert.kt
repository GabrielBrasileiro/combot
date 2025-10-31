package br.com.gabrielbrasileiro.sample.alert.combot

import androidx.compose.ui.test.onNodeWithText
import br.com.gabrielbrasileiro.combot.core.CombotAssert

class AlertCombotAssert : CombotAssert() {

    fun checkDefaultMessage() {
        onNodeWithText("An alert being received!").assertExists()
    }

}