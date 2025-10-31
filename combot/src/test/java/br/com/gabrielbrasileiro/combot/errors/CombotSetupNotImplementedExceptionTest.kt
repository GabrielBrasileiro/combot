package br.com.gabrielbrasileiro.combot.errors

import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class CombotSetupNotImplementedExceptionTest {

    @Test
    fun `CombotSetupNotImplementedException init Should emit correct message`() {
        // Given
        val expectedMessage = "Combot setup not implemented, please check your definitions!"

        // When
        val result = runCatching { throw CombotSetupNotImplementedException() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `CombotSetupNotImplementedException init Should be instance of Exception`() {
        // Given
        val exception = CombotSetupNotImplementedException()

        // When
        val result = runCatching { throw exception }

        // Then
        assertTrue(result.exceptionOrNull() is Exception)
    }
}