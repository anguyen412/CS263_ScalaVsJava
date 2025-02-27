package simple_lang.tokenizer

sealed trait Token

//types
case object IntToken extends Token
case object BoolToken extends Token
case object StringToken extends Token

//binary operators
case object PlusToken extends Token
case object MinusToken extends Token
case object LessThanToken extends Token
case object GreaterThanToken extends Token
case object ExactlyEqualsToken extends Token
case object NotEqualsToken extends Token

//unary operators
case object NotUnaryToken extends Token
case object MinusUnaryToken extends Token

//expressions
case class VariableToken(value: String) extends Token
case class IntegerLiteralToken(value: String) extends Token
case object TrueBooleanLiteralToken extends Token
case object FalseBooleanLiteralToken extends Token
case class StringLiteralToken(value: String) extends Token

//other reserved words and symbols
case object IfToken extends Token
case object ElseToken extends Token
case object WhileToken extends Token
case object PrintToken extends Token
case object LeftParenToken extends Token
case object RightParenToken extends Token
case object LeftCurlyBraceToken extends Token
case object RightCurlyBraceToken extends Token
