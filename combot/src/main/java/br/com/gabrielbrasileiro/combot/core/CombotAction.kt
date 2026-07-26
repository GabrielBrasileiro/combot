package br.com.gabrielbrasileiro.combot.core

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import br.com.gabrielbrasileiro.combot.provider.CombotRule

/**
 * Base class for defining actions in a Combot test arrangement.
 *
 * This class delegates all [CombotRule] operations to the
 * provided [rule] instance, allowing test actions to interact with Compose UI elements.
 *
 * @property rule The [CombotRule] used to perform UI interactions.
 *   Defaults to the rule instance provided by [CombotProvider.requireRule], which ensures
 *   that a valid rule context is available.
 *
 * Usage:
 * ```
 * class MyAction : CombotAction() {
 *     fun clickButton() {
 *         onNodeWithText("Submit").performClick()
 *     }
 * }
 * ```
 *
 * This class is typically extended in the `action` stage of a [CombotArrangement] to define
 * the user interactions to perform during testing.
 */
open class CombotAction(
    rule: CombotRule = CombotProvider.requireRule()
) : CombotRule by rule
