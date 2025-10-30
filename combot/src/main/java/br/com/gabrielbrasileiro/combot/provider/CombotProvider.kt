package br.com.gabrielbrasileiro.combot.provider

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.errors.CombotSemanticsNotPresentError
import br.com.gabrielbrasileiro.combot.provider.CombotProvider.requireSemantics

/**
 * Internal provider responsible for managing and exposing the current
 * [CombotSemantics] instance used across Combot components.
 *
 * This object acts as a central access point for Compose test semantics,
 * ensuring that test actions, assertions, and setups share the same
 * [SemanticsNodeInteractionsProvider] context.
 *
 * Typical usage:
 * - The semantics are automatically set through a [CombotArrangement] or test rule.
 * - The semantics can be accessed by calling [requireSemantics].
 * - If the semantics have not been initialized, a [CombotSemanticsNotPresentError] is thrown.
 */
internal object CombotProvider {

    private var semantics: CombotSemantics? = null

    /**
     * Sets the current [CombotSemantics] instance to be used by Combot components.
     *
     * This method is typically called automatically by the test rule or arrangement
     * setup, and should not be manually invoked in regular test cases.
     *
     * @param semantics The [CombotSemantics] instance to register, or `null` to clear it.
     */
    fun setSemantics(semantics: CombotSemantics?) {
        this.semantics = semantics
    }

    /**
     * Returns the currently registered [CombotSemantics] instance.
     *
     * If no semantics have been set, this method throws a [CombotSemanticsNotPresentError],
     * indicating that a test is attempting to access semantics outside a properly configured
     * Combot environment.
     *
     * @throws CombotSemanticsNotPresentError if the semantics instance is not available.
     */
    fun requireSemantics(): CombotSemantics {
        return semantics ?: throw CombotSemanticsNotPresentError()
    }
}
