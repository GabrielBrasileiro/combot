package br.com.gabrielbrasileiro.sample.message.domain.usecase

import br.com.gabrielbrasileiro.sample.message.domain.repository.NewMessageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class NewMessageUseCase(
    private val newMessageRepository: NewMessageRepository,
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
) {

    fun getMessage(): Flow<String> {
        return newMessageRepository
            .getMessage()
            .flowOn(dispatchers)
    }

}
