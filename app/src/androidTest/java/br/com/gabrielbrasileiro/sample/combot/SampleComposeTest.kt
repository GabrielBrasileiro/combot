package br.com.gabrielbrasileiro.sample.combot

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.rule.createCombotRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SampleCombotSetup : CombotSetup() {
    override fun onStart() {}
    override fun onEnd() {}
}

class SampleCombotAction : CombotAction(createComposeRule()) {
    fun executeA() {

    }
}

class SampleCombotAssert : CombotAssert() {
    fun assertA() {

    }
}

class SampleComposeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @get:Rule
    val combotRule = createCombotRule(
        rule = composeRule,
        setup = ::SampleCombotSetup,
        action = ::SampleCombotAction,
        assert = ::SampleCombotAssert
    )

    @Before
    fun setup() {

    }

    @Test
    fun tapToLoad_shouldShowA_when(): Unit = with(combotRule.arrangement) {
        setup {

        } action {
            executeA()
        } assert {
            assertA()
        }
    }

}