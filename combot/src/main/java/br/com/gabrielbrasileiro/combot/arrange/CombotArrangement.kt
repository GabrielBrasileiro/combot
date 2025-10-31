package br.com.gabrielbrasileiro.combot.arrange

import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.defaults.CombotActionDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotAssertDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault
import br.com.gabrielbrasileiro.combot.errors.CombotActionNotImplementedException
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException
import br.com.gabrielbrasileiro.combot.errors.CombotSetupNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics

/**
 * Encapsulates a full Combot test arrangement including setup, action, and assert stages.
 *
 * This class coordinates the lifecycle of a Combot-based test, ensuring that setup, action, and
 * assert blocks are executed in the correct context. It also validates the presence of custom
 * implementations and emits exceptions if default stages are called without user definition.
 *
 * @param R The [CombotSemantics] type representing UI semantics or contextual accessors.
 * @param STP The [CombotSetup] type defining initialization logic before actions and assertions.
 * @param ACT The [CombotAction] type defining user actions or events to execute.
 * @param AST The [CombotAssert] type defining validation or verification logic.
 *
 * @property rule The semantics provider used for the test.
 */
class CombotArrangement<R : CombotSemantics, STP : CombotSetup, ACT : CombotAction, AST : CombotAssert> internal constructor(
    @PublishedApi internal val rule: R,
    private val setup: (() -> STP),
    private val action: (() -> ACT),
    private val assert: (() -> AST)
) {

    /**
     * Lazily provides the setup stage instance scope.
     *
     * If the default setup [CombotSetupDefault] is used without a custom definition,
     * calling [setup] will emit a [CombotSetupNotImplementedException].
     */
    @PublishedApi
    internal val setupScope by lazy { setup() }

    /**
     * Lazily provides the action stage instance scope.
     *
     * If the default action [CombotActionDefault] is used without a custom definition,
     * calling [action] will emit a [CombotActionNotImplementedException].
     */
    @PublishedApi
    internal val actionScope by lazy { action() }

    /**
     * Lazily provides the assert stage instance scope.
     *
     * If the default assert [CombotAssertDefault] is used without a custom definition,
     * calling [assert] will emit a [CombotAssertNotImplementedException].
     */
    @PublishedApi
    internal val assertScope by lazy { assert() }

    init {
        CombotProvider.setSemantics(rule)
    }

    /**
     * Executes the setup stage of the arrangement.
     *
     * @param onSetup Lambda with the [STP] instance and the [rule] to define setup logic.
     * @return This [CombotArrangement] instance to allow chaining.
     *
     * If [setupScope] is a [CombotSetupDefault], calling this method will throw
     * [CombotSetupNotImplementedException].
     */
    inline infix fun setup(
        onSetup: STP.(rule: R) -> Unit
    ): CombotArrangement<R, STP, ACT, AST> {
        val currentSetupScope = setupScope

        if (currentSetupScope is CombotSetupDefault) {
            currentSetupScope.emitCombotSetupError()
        } else {
            onSetup(currentSetupScope, rule)
        }

        return this
    }

    /**
     * Executes the action stage of the arrangement.
     *
     * @param onAction Lambda with the [ACT] instance to define action logic.
     * @return This [CombotArrangement] instance to allow chaining.
     *
     * If [actionScope] is a [CombotActionDefault], calling this method will throw
     * [CombotActionNotImplementedException].
     */
    inline infix fun action(
        onAction: ACT.() -> Unit
    ): CombotArrangement<R, STP, ACT, AST> {
        val currentActionScope = actionScope

        if (currentActionScope is CombotActionDefault) {
            currentActionScope.emitCombotActionError()
        } else {
            onAction(currentActionScope)
        }

        return this
    }

    /**
     * Executes the assert stage of the arrangement.
     *
     * @param onAssert Lambda with the [AST] instance to define assert logic.
     * @return This [CombotArrangement] instance to allow chaining.
     *
     * If [assertScope] is a [CombotAssertDefault], calling this method will throw
     * [br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException].
     */
    inline infix fun assert(
        onAssert: AST.() -> Unit
    ): CombotArrangement<R, STP, ACT, AST> {
        val currentAssertScope = assertScope

        if (currentAssertScope is CombotAssertDefault) {
            currentAssertScope.emitCombotAssertError()
        } else {
            onAssert(currentAssertScope)
        }

        return this
    }

}
