package br.com.gabrielbrasileiro.combot.errors

import java.lang.Exception

class CombotComposeTestRuleNotAvailableError : Exception(
    "ComposeTestRule not defined!"
)