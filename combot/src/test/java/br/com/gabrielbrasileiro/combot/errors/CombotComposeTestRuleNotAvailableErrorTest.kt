package br.com.gabrielbrasileiro.combot.errors

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CombotComposeTestRuleNotAvailableErrorTest {

    @Test
    fun `CombotSemanticsNotPresentError init Should emit correct message`() {
        // Given
        val expectedMessage = "ComposeTestRule not defined!"

        // When
        val result = runCatching { throw CombotComposeTestRuleNotAvailableError() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `CombotSemanticsNotPresentError init Should be instance of Exception`() {
        // Given
        val exception = CombotComposeTestRuleNotAvailableError()

        // When
        val result = runCatching { throw exception }

        // Then
        assertTrue(result.exceptionOrNull() is Exception)
    }

}