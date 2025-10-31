package br.com.gabrielbrasileiro.combot.core

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics

/**
 * Base class for defining assertions in a Combot test arrangement.
 *
 * This class delegates all [CombotSemantics] operations to the
 * provided [semantics] instance, allowing test assertions to verify Compose UI elements.
 *
 * @property semantics The [CombotSemantics] used to perform UI assertions.
 *   Defaults to the semantics instance provided by [CombotProvider.requireSemantics], ensuring
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
    semantics: CombotSemantics = CombotProvider.requireSemantics()
) : CombotSemantics by semantics
