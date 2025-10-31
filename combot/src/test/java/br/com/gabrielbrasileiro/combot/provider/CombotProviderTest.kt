package br.com.gabrielbrasileiro.combot.provider

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.errors.CombotSemanticsNotPresentError
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CombotProviderTest {

    private val provider = mockk<SemanticsNodeInteractionsProvider>()

    @Before
    fun setup() {
        CombotProvider.setSemantics(null)
    }

    @Test
    fun `setSemantics Should store and retrieve the same instance`() {
        // Given
        CombotProvider.setSemantics(provider)

        // When
        val result = runCatching { CombotProvider.requireSemantics() }

        // Then
        assertNotNull(result.getOrNull())
        assertSame(provider, result.getOrNull())
    }

    @Test
    fun `requireSemantics Should emit an exception When semantics not provided`() {
        // Given
        CombotProvider.setSemantics(null)

        // When
        val result = runCatching { CombotProvider.requireSemantics() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotSemanticsNotPresentError)
    }

    @Test
    fun `requireSemantics Should emit an exception When set semantics clear the instance`() {
        // Given
        CombotProvider.setSemantics(provider)
        CombotProvider.setSemantics(null)

        // When
        val result = runCatching { CombotProvider.requireSemantics() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotSemanticsNotPresentError)
    }

}
