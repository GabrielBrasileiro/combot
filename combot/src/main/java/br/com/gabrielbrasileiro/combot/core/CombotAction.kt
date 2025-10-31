package br.com.gabrielbrasileiro.combot.core

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics

/**
 * Base class for defining actions in a Combot test arrangement.
 *
 * This class delegates all [CombotSemantics] operations to the
 * provided [semantics] instance, allowing test actions to interact with Compose UI elements.
 *
 * @property semantics The [CombotSemantics] used to perform UI interactions.
 *   Defaults to the semantics instance provided by [CombotProvider.requireSemantics], which ensures
 *   that a valid semantics context is available.
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
    semantics: CombotSemantics = CombotProvider.requireSemantics()
) : CombotSemantics by semantics
