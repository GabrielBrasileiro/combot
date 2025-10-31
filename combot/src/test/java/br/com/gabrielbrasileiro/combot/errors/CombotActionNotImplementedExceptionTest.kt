package br.com.gabrielbrasileiro.combot.errors

import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class CombotActionNotImplementedExceptionTest {

    @Test
    fun `CombotActionNotImplementedException init Should emit correct message`() {
        // Given
        val expectedMessage = "Combot action not implemented, please check your definitions"

        // When
        val result = runCatching { throw CombotActionNotImplementedException() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `CombotActionNotImplementedException init Should be instance of Exception`() {
        // Given
        val exception = CombotActionNotImplementedException()

        // When
        val result = runCatching { throw exception }

        // Then
        assertTrue(result.exceptionOrNull() is Exception)
    }

}