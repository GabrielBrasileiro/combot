package br.com.gabrielbrasileiro.combot.defaults

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.errors.CombotActionNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotActionDefaultTest {

    private val provider = mockk<SemanticsNodeInteractionsProvider>()

    @Test
    fun `CombotActionDefault Should throw CombotAssertNotImplementedException when instantiated`() {
        // Given
        CombotProvider.setSemantics(provider)

        // When
        val result = runCatching { CombotActionDefault() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotActionNotImplementedException)
    }

}