package br.com.gabrielbrasileiro.combot.defaults

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotAssertDefaultTest {

    private val provider = mockk<SemanticsNodeInteractionsProvider>()

    @Test
    fun `emitCombotAssertError Should throw CombotAssertNotImplementedException when instantiated`() {
        // Given
        CombotProvider.setSemantics(provider)

        val combotAssert = CombotAssertDefault()

        // When
        val result = runCatching { combotAssert.emitCombotAssertError() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotAssertNotImplementedException)
    }

}