package br.com.gabrielbrasileiro.combot.rule

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.defaults.CombotActionDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotAssertDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Combot Test Rule.
 */
class CombotTestRule<R : CombotSemantics, STP : CombotSetup, ACT : CombotAction, AST : CombotAssert>(
    val arrangement: CombotArrangement<R, STP, ACT, AST>
) : TestWatcher() {

    private val setup = arrangement.setupScope

    public override fun starting(description: Description?) {
        super.starting(description)
        setup.onStartContext()
    }

    public override fun finished(description: Description?) {
        super.finished(description)
        setup.onFinishContext()
    }
}

/**
 * Creates a `CombotTestRule` with a complete arrangement: setup, action, and assert.
 *
 * This is used when you want to fully specify all three stages of the combot test flow.
 *
 * @param rule The semantics provider to be used for the test.
 * @param setup Provides the setup stage instance.
 * @param action Provides the action stage instance.
 * @param assert Provides the assert stage instance.
 *
 * @return A [CombotTestRule] encapsulating the full arrangement.
 */
@JvmName("createCombotRuleNoSetup")
fun <R : CombotSemantics, STP : CombotSetup, ACT : CombotAction, AST : CombotAssert> createCombotRule(
    rule: R,
    setup: () -> STP,
    action: () -> ACT,
    assert: () -> AST,
): CombotTestRule<R, STP, ACT, AST> {
    val arrange = CombotArrangement(rule, setup, action, assert)
    return CombotTestRule(arrange)
}

/**
 * Creates a `CombotTestRule` without a setup stage.
 *
 * Uses a default setup (`CombotSetupDefault`) and allows specifying action and assert stages.
 *
 * @param rule The semantics provider to be used for the test.
 * @param action Provides the action stage instance.
 * @param assert Provides the assert stage instance.
 *
 * @return A [CombotTestRule] with default setup and provided action/assert.
 */
@JvmName("createCombotRuleNoSetup")
fun <R : CombotSemantics, ACT : CombotAction, AST : CombotAssert> createCombotRule(
    rule: R,
    action: () -> ACT,
    assert: () -> AST
): CombotTestRule<R, *, ACT, AST> {
    val arrange = CombotArrangement(
        rule = rule,
        setup = { CombotSetupDefault() },
        action = action,
        assert = assert
    )

    return CombotTestRule(arrange)
}

/**
 * Creates a [CombotTestRule] without an action stage.
 *
 * Uses a default action (`CombotActionDefault`) and allows specifying setup and assert stages.
 *
 * @param rule The semantics provider to be used for the test.
 * @param setup Provides the setup stage instance.
 * @param assert Provides the assert stage instance.
 *
 * @return A [CombotTestRule] with default action and provided setup/assert.
 */
@JvmName("createCombotRuleNoAction")
fun <R : CombotSemantics, STP : CombotSetup, AST : CombotAssert> createCombotRule(
    rule: R,
    setup: () -> STP,
    assert: () -> AST
): CombotTestRule<R, STP, *, AST> {
    val arrange = CombotArrangement(
        rule = rule,
        setup = setup,
        action = { CombotActionDefault() },
        assert = assert
    )

    return CombotTestRule(arrange)
}

/**
 * Creates a [CombotTestRule] without an assert stage.
 *
 * Uses a default assert (`CombotAssertDefault`) and allows specifying setup and action stages.
 *
 * @param rule The semantics provider to be used for the test.
 * @param setup Provides the setup stage instance.
 * @param action Provides the action stage instance.
 *
 * @return A [CombotTestRule] with default assert and provided setup/action.
 */
@JvmName("createCombotRuleNoAssert")
fun <R : CombotSemantics, STP : CombotSetup, ACT : CombotAction> createCombotRule(
    rule: R,
    setup: () -> STP,
    action: () -> ACT
): CombotTestRule<R, STP, ACT, *> {
    val arrange = CombotArrangement(
        rule = rule,
        setup = setup,
        action = action,
        assert = { CombotAssertDefault() }
    )

    return CombotTestRule(arrange)
}

@JvmName("createCombotRuleOnlyAssert")
fun <R : CombotSemantics, AST : CombotAssert> createCombotRule(
    rule: R,
    assert: () -> AST
): CombotTestRule<R, *, *, AST> {
    val arrange = CombotArrangement(
        rule = rule,
        setup = { CombotSetupDefault() },
        action = { CombotActionDefault() },
        assert = assert
    )

    return CombotTestRule(arrange)
}