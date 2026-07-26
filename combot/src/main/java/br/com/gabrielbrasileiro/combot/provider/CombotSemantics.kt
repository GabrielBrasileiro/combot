package br.com.gabrielbrasileiro.combot.provider

import androidx.compose.ui.test.junit4.ComposeTestRule

/**
 * Type alias that represents the Compose testing semantics provider.
 *
 * This alias simplifies the reference to [ComposeTestRule],
 * which is used internally by Combot to interact with Compose UI elements during tests.
 */
typealias CombotRule = ComposeTestRule