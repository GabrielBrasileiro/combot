package br.com.gabrielbrasileiro.sample.message.data.repository

import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class NewMessageRepositoryImplTest {

    private val repository = NewMessageRepositoryImpl()

    @Test
    fun `getMessage Should emit expected message`() = runBlocking {
        // Given
        val expectedMessage = "Your new message is ready!"

        // When
        val result = repository.getMessage().single()

        // Then
        assertEquals(expectedMessage, result)
    }

}
