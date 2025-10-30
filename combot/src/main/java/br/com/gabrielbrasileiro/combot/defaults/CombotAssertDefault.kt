package br.com.gabrielbrasileiro.combot.defaults

import br.com.gabrielbrasileiro.combot.core.CombotAssert
import br.com.gabrielbrasileiro.combot.errors.CombotAssertNotImplementedException

@PublishedApi
internal class CombotAssertDefault : CombotAssert() {

    init {
        throw CombotAssertNotImplementedException()
    }

}
