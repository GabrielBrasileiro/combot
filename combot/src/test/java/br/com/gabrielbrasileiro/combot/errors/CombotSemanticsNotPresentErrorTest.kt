package br.com.gabrielbrasileiro.combot.errors

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotSemanticsNotPresentErrorTest {

    @Test
    fun `CombotSemanticsNotPresentError init Should emit correct message`() {
        // Given
        val expectedMessage = "SemanticsNodeInteractionsProvider not defined!"

        // When
        val result = runCatching { throw CombotSemanticsNotPresentError() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `CombotSemanticsNotPresentError init Should be instance of Exception`() {
        // Given
        val exception = CombotSemanticsNotPresentError()

        // When
        val result = runCatching { throw exception }

        // Then
        assertTrue(result.exceptionOrNull() is Exception)
    }

}