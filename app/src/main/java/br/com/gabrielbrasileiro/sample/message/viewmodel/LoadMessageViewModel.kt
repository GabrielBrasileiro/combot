package br.com.gabrielbrasileiro.sample.message.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabrielbrasileiro.sample.message.domain.usecase.DefaultMessageUseCase
import br.com.gabrielbrasileiro.sample.message.domain.usecase.NewMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoadMessageViewModel(
    private val newMessageUseCase: NewMessageUseCase,
    defaultMessageUseCase: DefaultMessageUseCase
) : ViewModel(), LoadMessageAction {

    private val _message = MutableStateFlow(defaultMessageUseCase.getDefaultMessage())
    val message: StateFlow<String> = _message

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    override fun loadNewMessage() {
        _loading.value = true

        viewModelScope.launch {
            val message = newMessageUseCase
                .getMessage()
                .single()

            _message.update { message }
            _loading.value = false
        }
    }

}
