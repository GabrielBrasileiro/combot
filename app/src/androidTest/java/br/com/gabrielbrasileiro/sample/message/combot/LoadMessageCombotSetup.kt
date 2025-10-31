package br.com.gabrielbrasileiro.sample.message.combot

import androidx.test.platform.app.InstrumentationRegistry
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.sample.message.consts.NEW_MESSAGE
import br.com.gabrielbrasileiro.sample.message.di.LoadMessageDependencyInjection
import br.com.gabrielbrasileiro.sample.message.domain.repository.NewMessageRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class LoadMessageCombotSetup : CombotSetup() {

    private val newMessageRepository = mockk<NewMessageRepository>()

    override fun onStart() {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
        }

        loadKoinModules(LoadMessageDependencyInjection().getModules())
        loadKoinModules(module {
            factory<NewMessageRepository> { newMessageRepository }
        })
    }

    override fun onFinished() {
        stopKoin()
    }

    fun setupNewMessage() {
        every { newMessageRepository.getMessage() } returns flowOf(NEW_MESSAGE)
    }

}