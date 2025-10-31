package br.com.gabrielbrasileiro.combot.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.errors.CombotSemanticsNotPresentError
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotAssertTest {

    private val provider = mockk<SemanticsNodeInteractionsProvider>()

    @Test
    fun `CombotAssert init Should provide a default SemanticsNodeInteractionsProvider`() {
        // Given
        CombotProvider.setSemantics(provider)

        // When
        val result = runCatching { CombotAssert() }

        // Then
        assertNotNull(result)
    }

    @Test
    fun `CombotAssert init Should emit an exception When semantics not provided`() {
        // Given
        CombotProvider.setSemantics(null)

        // When
        val result = runCatching { CombotAssert() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotSemanticsNotPresentError)
    }
}