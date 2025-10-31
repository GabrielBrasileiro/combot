package br.com.gabrielbrasileiro.combot.core

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.errors.CombotSetupNotImplementedException
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault

/**
 * Base class representing the setup phase of a Combot test arrangement.
 *
 * This class defines lifecycle hooks that can be overridden to configure or
 * prepare the testing environment before and after a test action is executed.
 * It is typically used in the `setup` stage of a [CombotArrangement].
 *
 * If a test arrangement uses [CombotSetupDefault] instead of a custom setup,
 * calling the `setup` stage will throw a [CombotSetupNotImplementedException].
 *
 * Subclasses can override [onStart] and [onFinished] to define custom setup or
 * teardown logic specific to each test.
 *
 * Example usage:
 * ```
 * class MyCombotSetup : CombotSetup() {
 *     override fun onStart() {
 *         // Initialize mocks, set UI state, or configure dependencies
 *     }
 *
 *     override fun onFinished() {
 *         // Clean up resources or reset state after the test
 *     }
 * }
 * ```
 */
open class CombotSetup {

    /**
     * Called before the test actions are executed.
     *
     * Override this method to define initialization logic, such as preparing
     * mock data, setting UI state, or configuring dependencies needed for the
     * test.
     */
    protected open fun onStart() {}

    /**
     * Called after the test actions are executed.
     *
     * Override this method to define cleanup logic, such as resetting state,
     * clearing resources, or validating postconditions required for subsequent tests.
     */
    protected open fun onFinished() {}

    /**
     * Internal method called by [CombotTestRule] before test execution.
     *
     * Invokes [onStart] to perform setup logic.
     */
    internal fun onStartContext() {
        onStart()
    }

    /**
     * Internal method called by [CombotTestRule] after test execution.
     *
     * Invokes [onFinished] to perform teardown logic.
     */
    internal fun onFinishContext() {
        onFinished()
    }
}