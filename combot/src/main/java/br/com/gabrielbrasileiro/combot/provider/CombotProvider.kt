package br.com.gabrielbrasileiro.combot.provider

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.ComposeTestRule
import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.errors.CombotComposeTestRuleNotAvailableError
import br.com.gabrielbrasileiro.combot.provider.CombotProvider.requireRule

/**
 * Internal provider responsible for managing and exposing the current
 * [CombotRule] instance used across Combot components.
 *
 * This object acts as a central access point for Compose test semantics,
 * ensuring that test actions, assertions, and setups share the same
 * [SemanticsNodeInteractionsProvider] context.
 *
 * Typical usage:
 * - The semantics are automatically set through a [CombotArrangement] or test rule.
 * - The semantics can be accessed by calling [requireRule].
 * - If the semantics have not been initialized, a [CombotComposeTestRuleNotAvailableError] is thrown.
 */
internal object CombotProvider {

    private var rule: ComposeTestRule? = null

    /**
     * Sets the current [ComposeTestRule] instance to be used by Combot components.
     *
     * This method is typically called automatically by the test rule or arrangement
     * setup, and should not be manually invoked in regular test cases.
     *
     * @param rule The [ComposeTestRule] instance to register, or `null` to clear it.
     */
    fun setRule(rule: ComposeTestRule?) {
        this.rule = rule
    }

    /**
     * Returns the currently registered [ComposeTestRule] instance.
     *
     * If no semantics have been set, this method throws a [CombotComposeTestRuleNotAvailableError],
     * indicating that a test is attempting to access semantics outside a properly configured
     * Combot environment.
     *
     * @throws CombotComposeTestRuleNotAvailableError if the semantics instance is not available.
     */
    fun requireRule(): ComposeTestRule {
        return rule ?: throw CombotComposeTestRuleNotAvailableError()
    }
}
