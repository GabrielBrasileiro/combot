package br.com.gabrielbrasileiro.combot.defaults

import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.errors.CombotActionNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotActionDefaultTest {

    private val provider = mockk<ComposeTestRule>()

    @Test
    fun `emitCombotActionError Should throw CombotAssertNotImplementedException when instantiated`() {
        // Given
        CombotProvider.setRule(provider)

        val combotAction = CombotActionDefault()

        // When
        val result = runCatching { combotAction.emitCombotActionError() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotActionNotImplementedException)
    }

}