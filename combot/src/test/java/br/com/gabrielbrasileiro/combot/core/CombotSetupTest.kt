package br.com.gabrielbrasileiro.combot.core

import org.junit.Test

class CombotSetupTest {

    @Test
    fun `onStart Should be called without throwing any exception by default`() {
        // Given
        val setup = CombotSetup()

        // When
        setup.onStartContext()
    }

    @Test
    fun `onEnd Should be called without throwing any exception by default`() {
        // Given
        val setup = CombotSetup()

        // When
        setup.onFinishContext()
    }

}
