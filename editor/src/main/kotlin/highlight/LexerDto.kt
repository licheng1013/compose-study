package highlight

import lexer.core.LexerE

data class LexerDto(
    var start: Int = 0,
    var end: Int = 0,
    var type: LexerE = LexerE.NONE
)
