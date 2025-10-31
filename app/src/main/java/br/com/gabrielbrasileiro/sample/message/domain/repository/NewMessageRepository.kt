package br.com.gabrielbrasileiro.sample.message.domain.repository

import kotlinx.coroutines.flow.Flow

interface NewMessageRepository {
    fun getMessage(): Flow<String>
}