package br.com.gabrielbrasileiro.combot.core

/**
 * Base class representing the setup phase of a Combot test arrangement.
 *
 * This class defines lifecycle hooks that can be overridden to configure or
 * prepare the testing environment before and after a test action is executed.
 *
 * Subclasses can override [onStart] and [onEnd] to define custom setup or
 * teardown logic specific to each test.
 */
open class CombotSetup {

    /**
     * Called before the test actions are executed.
     *
     * Override this method to define initialization logic, such as preparing
     * mock data, setting UI state, or configuring dependencies needed for the
     * test.
     */
    open fun onStart() {}

    /**
     * Called after the test actions are executed.
     *
     * Override this method to define cleanup logic, such as resetting state,
     * clearing resources, or validating postconditions required for subsequent tests.
     */
    open fun onEnd() {}
}