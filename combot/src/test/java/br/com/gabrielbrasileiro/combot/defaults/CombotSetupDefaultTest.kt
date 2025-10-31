package br.com.gabrielbrasileiro.combot.defaults

import br.com.gabrielbrasileiro.combot.errors.CombotSetupNotImplementedException
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotSetupDefaultTest {

    @Test
    fun `emitCombotSetupError Should throw CombotSetupNotImplementedException When is called`() {
        // Given
        val setup = CombotSetupDefault()

        // When
        val result = runCatching { setup.emitCombotSetupError() }

        // When / Then
        assertTrue(result.exceptionOrNull() is CombotSetupNotImplementedException)
    }

}
