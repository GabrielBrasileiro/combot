package br.com.gabrielbrasileiro.sample.message.viewmodel

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import br.com.gabrielbrasileiro.sample.message.domain.usecase.DefaultMessageUseCase
import br.com.gabrielbrasileiro.sample.message.domain.usecase.NewMessageUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoadMessageViewModelTest {

    private val defaultMessageUseCase = DefaultMessageUseCase()
    private val newMessageUseCase = mockk<NewMessageUseCase>()
    private val viewModel = LoadMessageViewModel(newMessageUseCase, defaultMessageUseCase)

    @Test
    fun `init Should start with a default message`() = runTest {
        // Given
        val expected = "Welcome to combot!"

        viewModel.message.test {
            val initial = awaitItem()

            // Then
            assertEquals(expected, initial)
        }
    }

    @Test
    fun `init Should start with loading state false`() = runTest {
        // Given
        val expected = false

        viewModel.loading.test {
            val initial = awaitItem()

            // Then
            assertEquals(expected, initial)
        }
    }

    @Test
    fun `loadNewMessage Should update message and loading states`() = runTest {
        // Given
        val newMessage = "Hello Combot!"
        val skipFirst = 1

        coEvery { newMessageUseCase.getMessage() } returns flow { emit(newMessage) }

        turbineScope {
            val messageState = viewModel.message.testIn(backgroundScope)
            val loadingState = viewModel.loading.testIn(backgroundScope)

            // When
            viewModel.loadNewMessage()

            // Then
            messageState.skipItems(skipFirst)
            loadingState.skipItems(skipFirst)

            assertTrue(loadingState.awaitItem())
            assertEquals(newMessage, messageState.awaitItem())
            assertFalse(loadingState.awaitItem())
        }
    }
}