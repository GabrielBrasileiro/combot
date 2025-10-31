package br.com.gabrielbrasileiro.combot.arrange

import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.defaults.CombotActionDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotAssertDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault
import br.com.gabrielbrasileiro.combot.errors.CombotActionNotImplementedException
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException
import br.com.gabrielbrasileiro.combot.errors.CombotSetupNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics
import io.mockk.mockk
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotArrangementTest {

    private val semantics = mockk<FakeSemantics>()
    private val setup = mockk<FakeSetup>()
    private val action = mockk<FakeAction>()
    private val assert = mockk<FakeAssert>()

    @Test
    fun `setup Should be invoked When success`() {
        // Given
        val arrangement = createDefaultArrangement()
        var currentRule: CombotSemantics? = null

        // When
        val result = arrangement setup { rule ->
            currentRule = rule
        }

        // Then
        assertSame(currentRule, semantics)
        assertSame(arrangement, result)
    }

    @Test
    fun `setup Should throw error When CombotSetupDefault is passed`() {
        // Given
        val arrangement = CombotArrangement(
            rule = semantics,
            setup = { CombotSetupDefault() },
            action = { action },
            assert = { assert }
        )

        // When
        val result = runCatching { arrangement setup {} }

        // Then
        assertTrue(result.exceptionOrNull() is CombotSetupNotImplementedException)
    }

    @Test
    fun `action block Should be invoked When success`() {
        // Given
        val arrangement = createDefaultArrangement()
        var invoked = false

        // When
        val result = arrangement action { invoked = true }

        // Then
        assert(invoked)
        assertSame(arrangement, result)
    }

    @Test
    fun `action Should throw error When CombotActionDefault is used`() {
        // Given
        val arrangement = CombotArrangement(
            rule = semantics,
            setup = { setup },
            action = { CombotActionDefault() },
            assert = { assert }
        )

        // When
        val result = runCatching { arrangement action {} }

        // Then
        assertTrue(result.exceptionOrNull() is CombotActionNotImplementedException)
    }

    @Test
    fun `assert block Should be invoked When success`() {
        // Given
        val arrangement = createDefaultArrangement()
        var invoked = false

        // When
        arrangement assert { invoked = true }

        // Then
        assert(invoked)
    }

    @Test
    fun `assert Should throw error When CombotAssertDefault is used`() {
        // Given
        val arrangement = CombotArrangement(
            rule = semantics,
            setup = { setup },
            action = { action },
            assert = { CombotAssertDefault() }
        )

        // When
        val result = runCatching { arrangement assert {} }

        // Then
        assertTrue(result.exceptionOrNull() is CombotAssertNotImplementedException)
    }

    @Test
    fun `setupScope, actionScope, assertScope Should return correct instances`() {
        // When
        val arrangement = createDefaultArrangement()

        // Then
        assertSame(setup, arrangement.setupScope)
        assertSame(action, arrangement.actionScope)
        assertSame(assert, arrangement.assertScope)
    }

    private fun createDefaultArrangement() = CombotArrangement(
        rule = semantics,
        setup = { setup },
        action = { action },
        assert = { assert }
    )

    interface FakeSemantics : CombotSemantics
    class FakeSetup : CombotSetup()
    class FakeAction : CombotAction()
    class FakeAssert : CombotAssert()

}
