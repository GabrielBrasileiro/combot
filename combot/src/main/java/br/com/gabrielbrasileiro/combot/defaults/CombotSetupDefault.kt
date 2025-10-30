package br.com.gabrielbrasileiro.combot.defaults

import br.com.gabrielbrasileiro.combot.core.CombotSetup
import br.com.gabrielbrasileiro.combot.errors.CombotSetupNotImplementedException

@PublishedApi
internal class CombotSetupDefault : CombotSetup() {

    fun emitCombotSetupError() {
        throw CombotSetupNotImplementedException()
    }

}
