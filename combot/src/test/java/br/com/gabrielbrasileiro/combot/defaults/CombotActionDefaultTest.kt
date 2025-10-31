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
    fun `emitCombotActionError Should throw CombotAssertNotImplementedException when instantiated`() {
        // Given
        CombotProvider.setSemantics(provider)

        val combotAction = CombotActionDefault()

        // When
        val result = runCatching { combotAction.emitCombotActionError() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotActionNotImplementedException)
    }

}