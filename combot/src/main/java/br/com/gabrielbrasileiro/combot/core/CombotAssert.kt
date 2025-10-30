package br.com.gabrielbrasileiro.combot.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import br.com.gabrielbrasileiro.combot.provider.CombotProvider

open class CombotAssert(
    semantics: SemanticsNodeInteractionsProvider = CombotProvider.requireSemantics()
) : SemanticsNodeInteractionsProvider by semantics
