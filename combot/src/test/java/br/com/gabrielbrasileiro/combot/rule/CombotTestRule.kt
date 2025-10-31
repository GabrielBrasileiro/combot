package br.com.gabrielbrasileiro.combot.rule

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.defaults.CombotActionDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotAssertDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotTestRuleTest {

    private val semantics = mockk<CombotSemantics>()

    @Test
    fun `starting should call setup onStart`() {
        // Given
        val setup = FakeSetup()
        val arrangement = CombotArrangement(
            rule = semantics,
            setup = { setup },
            action = { FakeAction() },
            assert = { FakeAssert() }
        )
        val rule = CombotTestRule(arrangement)

        // When
        rule.starting(null)

        // Then
        assertTrue(setup.onStartCalled)
    }

    @Test
    fun `finished Should call setup onEnd`() {
        // Given
        val setup = FakeSetup()
        val arrangement = CombotArrangement(
            rule = semantics,
            setup = { setup },
            action = { FakeAction() },
            assert = { FakeAssert() }
        )
        val rule = CombotTestRule(arrangement)

        // When
        rule.finished(null)

        // Then
        assertTrue(setup.onEndCalled)
    }

    @Test
    fun `createCombotRule Should use CombotSetupDefault When setup not passed`() {
        // When
        val rule = createCombotRule(
            rule = semantics,
            action = { FakeAction() },
            assert = { FakeAssert() }
        )

        // Then
        assertTrue(rule.arrangement.setupScope is CombotSetupDefault)
    }

    @Test
    fun `createCombotRule Should use CombotActionDefault`() {
        // When
        val rule = createCombotRule(
            rule = semantics,
            setup = { FakeSetup() },
            assert = { FakeAssert() }
        )

        // Then
        assertTrue(rule.arrangement.actionScope is CombotActionDefault)
    }

    @Test
    fun `createCombotRule Should use CombotAssertDefault When assert not passed`() {
        // When
        val rule = createCombotRule(
            rule = semantics,
            setup = { FakeSetup() },
            action = { FakeAction() }
        )

        // Then
        assertTrue(rule.arrangement.assertScope is CombotAssertDefault)
    }

    @Test
    fun `createCombotRule Should use default setup and action When they not passed`() {
        // When
        val rule = createCombotRule(
            rule = semantics,
            assert = { FakeAssert() }
        )

        // Then
        assertTrue(rule.arrangement.setupScope is CombotSetupDefault)
        assertTrue(rule.arrangement.actionScope is CombotActionDefault)
    }

    private class FakeSetup : CombotSetup() {
        var onStartCalled = false
        var onEndCalled = false

        override fun onStart() {
            onStartCalled = true
        }

        override fun onFinished() {
            onEndCalled = true
        }
    }

    private class FakeAction : CombotAction()
    private class FakeAssert : CombotAssert()
}