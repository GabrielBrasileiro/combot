package br.com.gabrielbrasileiro.combot.arrange

import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.defaults.CombotActionDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotAssertDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault
import br.com.gabrielbrasileiro.combot.provider.CombotProvider
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics

class CombotArrangement<R : CombotSemantics, STP : CombotSetup, ACT : CombotAction, AST : CombotAssert> internal constructor(
    @PublishedApi internal val rule: R,
    private val setup: (() -> STP),
    private val action: (() -> ACT),
    private val assert: (() -> AST)
) {

    @PublishedApi
    internal val setupScope by lazy { setup() }

    @PublishedApi
    internal val actionScope by lazy { action() }

    @PublishedApi
    internal val assertScope by lazy { assert() }

    init {
        CombotProvider.setSemantics(rule)
    }

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
