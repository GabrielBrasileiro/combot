package br.com.gabrielbrasileiro.sample.message

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.gabrielbrasileiro.combot.rule.createCombotRule
import br.com.gabrielbrasileiro.sample.message.combot.LoadMessageCombotAction
import br.com.gabrielbrasileiro.sample.message.combot.LoadMessageCombotAssert
import br.com.gabrielbrasileiro.sample.message.combot.LoadMessageCombotSetup
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoadMessageTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val combotRule = createCombotRule(
        rule = composeRule,
        setup = ::LoadMessageCombotSetup,
        action = ::LoadMessageCombotAction,
        assert = ::LoadMessageCombotAssert
    )

    @Before
    fun setup() {
        composeRule.setContent { LoadMessageScreen() }
    }

    @Test
    fun onLoadOfAlert_ShouldShowTheDefaultMessage() {
        with(combotRule.arrangement) {
            assert {
                checkDefaultMessage()
            }
        }
    }

    @Test
    fun tapToGetANewMessage_ShouldLoadAnotherMessage() {
        with(combotRule.arrangement) {
            setup {
                setupNewMessage()
            } action {
                clickGetNewMessage()
            } assert {
                checkShowNewMessage()
            }
        }
    }

}