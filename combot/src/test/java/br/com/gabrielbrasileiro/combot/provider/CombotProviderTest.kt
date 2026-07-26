package br.com.gabrielbrasileiro.combot.provider

import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.errors.CombotComposeTestRuleNotAvailableError
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CombotProviderTest {

    private val provider = mockk<ComposeTestRule>()

    @Before
    fun setup() {
        CombotProvider.setRule(null)
    }

    @Test
    fun `setSemantics Should store and retrieve the same instance`() {
        // Given
        CombotProvider.setRule(provider)

        // When
        val result = runCatching { CombotProvider.requireRule() }

        // Then
        assertNotNull(result.getOrNull())
        assertSame(provider, result.getOrNull())
    }

    @Test
    fun `requireSemantics Should emit an exception When semantics not provided`() {
        // Given
        CombotProvider.setRule(null)

        // When
        val result = runCatching { CombotProvider.requireRule() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotComposeTestRuleNotAvailableError)
    }

    @Test
    fun `requireSemantics Should emit an exception When set semantics clear the instance`() {
        // Given
        CombotProvider.setRule(provider)
        CombotProvider.setRule(null)

        // When
        val result = runCatching { CombotProvider.requireRule() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotComposeTestRuleNotAvailableError)
    }

}
