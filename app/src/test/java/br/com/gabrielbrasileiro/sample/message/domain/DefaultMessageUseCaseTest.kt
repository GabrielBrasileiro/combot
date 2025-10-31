package br.com.gabrielbrasileiro.sample.message.domain

import br.com.gabrielbrasileiro.sample.message.domain.usecase.DefaultMessageUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultMessageUseCaseTest {

    private val useCase = DefaultMessageUseCase()

    @Test
    fun `getDefaultMessage Should return welcome message`() {
        // Given
        val expectedMessage = "Welcome to combot!"

        // When
        val actualMessage = useCase.getDefaultMessage()

        // Then
        assertEquals(expectedMessage, actualMessage)
    }

}
