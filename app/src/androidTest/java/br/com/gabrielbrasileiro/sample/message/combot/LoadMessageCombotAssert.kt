package br.com.gabrielbrasileiro.sample.message.combot

import androidx.compose.ui.test.onNodeWithText
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.sample.message.consts.DEFAULT_LOAD_MESSAGE
import br.com.gabrielbrasileiro.sample.message.consts.NEW_MESSAGE

class LoadMessageCombotAssert : CombotAssert() {

    fun checkDefaultMessage() {
        onNodeWithText(DEFAULT_LOAD_MESSAGE).assertExists()
    }

    fun checkShowNewMessage() {
        onNodeWithText(NEW_MESSAGE).assertExists()
    }


}