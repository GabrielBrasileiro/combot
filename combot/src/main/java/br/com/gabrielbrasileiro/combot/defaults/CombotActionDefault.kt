package br.com.gabrielbrasileiro.combot.defaults

import br.com.gabrielbrasileiro.combot.core.CombotAction
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException

@PublishedApi
internal class CombotActionDefault : CombotAction() {

    init {
        throw CombotAssertNotImplementedException()
    }

}
