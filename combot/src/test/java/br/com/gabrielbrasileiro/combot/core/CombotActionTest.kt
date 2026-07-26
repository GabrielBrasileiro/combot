package br.com.gabrielbrasileiro.combot.core

import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.errors.CombotComposeTestRuleNotAvailableError
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotActionTest {

    @Test
    fun `CombotAction init Should provide a default ComposeTestRule`() {
        // Given
        val provider = mockk<ComposeTestRule>(relaxed = true)

        CombotProvider.setRule(provider)

        // When
        val result = runCatching { CombotAction() }

        // Then
        assertNotNull(result)
    }

    @Test
    fun `CombotAction init Should emit an exception When semantics not provided`() {
        // Given
        CombotProvider.setRule(null)

        // When
        val result = runCatching { CombotAction() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotComposeTestRuleNotAvailableError)
    }
}