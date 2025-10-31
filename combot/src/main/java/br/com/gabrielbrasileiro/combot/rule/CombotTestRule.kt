package br.com.gabrielbrasileiro.combot.rule

import br.com.gabrielbrasileiro.combot.arrange.CombotArrangement
import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.defaults.CombotActionDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotAssertDefault
import br.com.gabrielbrasileiro.combot.defaults.CombotSetupDefault
import br.com.gabrielbrasileiro.combot.errors.CombotActionNotImplementedException
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException
import br.com.gabrielbrasileiro.combot.errors.CombotSetupNotImplementedException
import br.com.gabrielbrasileiro.combot.provider.CombotSemantics
import org.junit.rules.TestWatcher
import org.junit.runner.Description


/**
 * A [TestWatcher] implementation that provides lifecycle management for Combot test arrangements.
 *
 * For more information about the lifecycle read:
 * https://gabrielbrasileiro.dev
 *
 * This rule ensures that the setup and teardown logic of the [CombotSetup] stage is automatically
 * invoked when a test starts and finishes.
 *
 * It acts as the entry point for executing Combot-based test flows, coordinating the
 * setup, action, and assert stages defined in a [CombotArrangement].
 *
 * @param R The [CombotSemantics] type defining UI semantics or contextual accessors for the test.
 * @param STP The [CombotSetup] type representing initialization logic before actions and assertions.
 * @param ACT The [CombotAction] type representing the actions or events to perform.
 * @param AST The [CombotAssert] type representing validation or verification logic.
 *
 * @property arrangement The full [CombotArrangement] containing setup, action, and assert definitions.
 */
class CombotTestRule<R : CombotSemantics, STP : CombotSetup, ACT : CombotAction, AST : CombotAssert>(
    val arrangement: CombotArrangement<R, STP, ACT, AST>
) : TestWatcher() {

    private val setup = arrangement.setupScope

    /**
     * Called before each test begins.
     * Invokes the [CombotSetup.onStartContext] lifecycle hook to prepare the environment.
     */
    public override fun starting(description: Description?) {
        super.starting(description)
        setup.onStartContext()
    }

    /**
     * Called after each test completes.
     * Invokes the [CombotSetup.onFinishContext] lifecycle hook to clean up resources.
     */
    public override fun finished(description: Description?) {
        super.finished(description)
        setup.onFinishContext()
    }
}

/**
 * Creates a [CombotTestRule] with a complete arrangement: setup, action, and assert.
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
@JvmName("createCombotRule")
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
 * Uses a default setup [CombotSetupDefault] and allows specifying action and assert stages.
 *
 * If the setup phase of [CombotArrangement] is called without being explicitly defined,
 * a [CombotSetupNotImplementedException] will be emitted.
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
 * Uses a default action [CombotActionDefault] and allows specifying setup and assert stages.
 *
 * If the action phase of [CombotArrangement] is called without being explicitly defined,
 * a [CombotActionNotImplementedException] will be emitted.
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
 * Uses a default assert [CombotAssertDefault] and allows specifying setup and action stages.
 *
 * If the assert phase of [CombotArrangement] is called without being explicitly defined,
 * a [CombotAssertNotImplementedException] will be emitted.
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

/**
 * Creates a [CombotTestRule] with only an assert stage defined.
 *
 * Uses default setup [CombotSetupDefault] and action [CombotActionDefault] implementations.
 *
 * If the action phase of [CombotArrangement] is called without being explicitly defined,
 * a [CombotActionNotImplementedException] will be emitted.
 *
 * If the assert phase of [CombotArrangement] is called without being explicitly defined,
 * a [CombotAssertNotImplementedException] will be emitted.
 *
 * @param rule The semantics provider to be used for the test.
 * @param assert Provides the assert stage instance.
 *
 * @return A [CombotTestRule] with default setup/action and provided assert.
 */
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