package br.com.gabrielbrasileiro.sample.message.di

import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class LoadMessageDependencyInjectionTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `verify Should return true When invoke the graph`() {
        LoadMessageDependencyInjection().getModules().verify()
    }

}