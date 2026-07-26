package br.com.gabrielbrasileiro.combot.core

import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import br.com.gabrielbrasileiro.combot.provider.CombotRule

/**
 * Base class for defining assertions in a Combot test arrangement.
 *
 * This class delegates all [CombotRule] operations to the
 * provided [rule] instance, allowing test assertions to verify Compose UI elements.
 *
 * @property rule The [CombotRule] used to perform UI assertions.
 *   Defaults to the semantics instance provided by [CombotProvider.requireRule], ensuring
 *   a valid semantics context is available.
 *
 * Usage:
 * ```
 * class MyAssert : CombotAssert() {
 *     fun verifyButtonExists() {
 *         onNodeWithText("Submit").assertExists()
 *     }
 * }
 * ```
 *
 * This class is typically extended in the `assert` stage of a [CombotArrangement] to define
 * the validations or checks to perform during testing.
 */
open class CombotAssert(
    composeRule: ComposeTestRule = CombotProvider.requireRule()
) : ComposeTestRule by composeRule
