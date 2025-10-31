package br.com.gabrielbrasileiro.combot.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.errors.CombotSemanticsNotPresentError
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotActionTest {

    @Test
    fun `CombotAction init Should provide a default SemanticsNodeInteractionsProvider`() {
        // Given
        val provider = mockk<SemanticsNodeInteractionsProvider>(relaxed = true)

        CombotProvider.setSemantics(provider)

        // When
        val result = runCatching { CombotAction() }

        // Then
        assertNotNull(result)
    }

    @Test
    fun `CombotAction init Should emit an exception When semantics not provided`() {
        // Given
        CombotProvider.setSemantics(null)

        // When
        val result = runCatching { CombotAction() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotSemanticsNotPresentError)
    }
}