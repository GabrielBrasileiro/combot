package br.com.gabrielbrasileiro.combot.defaults

import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotAssertDefaultTest {

    private val provider = mockk<ComposeTestRule>()

    @Test
    fun `emitCombotAssertError Should throw CombotAssertNotImplementedException when instantiated`() {
        // Given
        CombotProvider.setRule(provider)

        val combotAssert = CombotAssertDefault()

        // When
        val result = runCatching { combotAssert.emitCombotAssertError() }

        // Then
        assertTrue(result.exceptionOrNull() is CombotAssertNotImplementedException)
    }

}
