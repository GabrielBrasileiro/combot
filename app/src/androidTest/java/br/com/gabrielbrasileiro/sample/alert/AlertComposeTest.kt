package br.com.gabrielbrasileiro.sample.alert

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.gabrielbrasileiro.combot.rule.createCombotRule
import br.com.gabrielbrasileiro.sample.alert.combot.AlertCombotAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlertComposeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val combotRule = createCombotRule(
        rule = composeRule,
        assert = ::AlertCombotAssert
    )

    @Before
    fun setup() {
        composeRule.setContent { AlertView() }
    }

    @Test
    fun onLoadOfAlert_ShouldShowTheDefaultMessage() {
        with(combotRule.arrangement) {
            assert {
                checkDefaultMessage()
            }
        }
    }

}