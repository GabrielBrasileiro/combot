package br.com.gabrielbrasileiro.sample.message.di

import br.com.gabrielbrasileiro.sample.message.data.repository.NewMessageRepositoryImpl
import br.com.gabrielbrasileiro.sample.message.domain.repository.NewMessageRepository
import br.com.gabrielbrasileiro.sample.message.domain.usecase.DefaultMessageUseCase
import br.com.gabrielbrasileiro.sample.message.domain.usecase.NewMessageUseCase
import br.com.gabrielbrasileiro.sample.message.viewmodel.LoadMessageViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class LoadMessageDependencyInjection {

    fun getModules() = module {
        factoryDomain()
        factoryViewModels()
        factoryData()
    }

    private fun Module.factoryDomain() {
        factory { NewMessageUseCase(newMessageRepository = get()) }
        factory { DefaultMessageUseCase() }
    }

    private fun Module.factoryViewModels() {
        viewModel {
            LoadMessageViewModel(
                newMessageUseCase = get(),
                defaultMessageUseCase = get()
            )
        }
    }

    private fun Module.factoryData() {
        factory<NewMessageRepository> { NewMessageRepositoryImpl() }
    }
}