package br.com.gabrielbrasileiro.combot.errors

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotAssertNotImplementedExceptionTest {

    @Test
    fun `CombotAssertNotImplementedException init Should emit correct message`() {
        // Given
        val expectedMessage = "Combot assert not implemented, please check your definitions"

        // When
        val result = runCatching { throw CombotAssertNotImplementedException() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `CombotAssertNotImplementedException init Should be instance of Exception`() {
        // Given
        val exception = CombotAssertNotImplementedException()

        // When
        val result = runCatching { throw exception }

        // Then
        assertTrue(result.exceptionOrNull() is Exception)
    }

}
