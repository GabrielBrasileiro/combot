package br.com.gabrielbrasileiro.sample.message.domain

import br.com.gabrielbrasileiro.sample.message.domain.repository.NewMessageRepository
import br.com.gabrielbrasileiro.sample.message.domain.usecase.NewMessageUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class NewMessageUseCaseTest {

    private val newMessageRepository = mockk<NewMessageRepository>()
    private val newMessageUseCase = NewMessageUseCase(
        newMessageRepository = newMessageRepository,
        dispatchers = Dispatchers.Unconfined
    )


    @Test
    fun `getMessage should emit expected message`(): Unit = runBlocking {
        // Given
        val expectedMessage = "Hello Combot!"

        coEvery { newMessageRepository.getMessage() } returns flow { emit(expectedMessage) }

        // When
        val result = newMessageUseCase.getMessage().single()

        // Then
        assertEquals(expectedMessage, result)
    }

}