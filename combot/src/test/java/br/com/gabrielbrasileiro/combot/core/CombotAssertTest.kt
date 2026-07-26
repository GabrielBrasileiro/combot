package br.com.gabrielbrasileiro.combot.core

import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.errors.CombotComposeTestRuleNotAvailableError
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotAssertTest {

    private val provider = mockk<ComposeTestRule>()

    @Test
    fun `CombotAssert init Should provide a default ComposeTestRule`() {
        // Given
        CombotProvider.setRule(provider)

        // When
        val result = runCatching { CombotAssert() }

        // Then
        assertNotNull(result)
    }

    @Test
    fun `CombotAssert init Should emit an exception When semantics not provided`() {
        // Given
        CombotProvider.setRule(null)

        // When
        val result = runCatching { CombotAssert() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotComposeTestRuleNotAvailableError)
    }
}