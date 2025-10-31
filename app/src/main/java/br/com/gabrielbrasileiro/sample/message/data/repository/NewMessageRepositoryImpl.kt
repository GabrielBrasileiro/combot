package br.com.gabrielbrasileiro.sample.message.data.repository

import br.com.gabrielbrasileiro.sample.message.domain.repository.NewMessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class NewMessageRepositoryImpl : NewMessageRepository {

    override fun getMessage(): Flow<String> {
        return flow { emit(MESSAGE) }
    }

    private companion object {
        const val MESSAGE = "Your new message is ready!"
    }
}
