package br.com.gabrielbrasileiro.sample.message.domain.usecase

class DefaultMessageUseCase {

    fun getDefaultMessage(): String = MESSAGE

    private companion object {
        const val MESSAGE = "Welcome to combot!"
    }
}
