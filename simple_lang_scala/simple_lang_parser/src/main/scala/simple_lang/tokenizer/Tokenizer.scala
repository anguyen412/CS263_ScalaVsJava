package simple_lang.tokenizer

import scala.util.matching.Regex
import scala.util.parsing.combinator._

class TokenizerException(message: String) extends Exception(message)

object Tokenizer extends RegexParsers {
    
    def reserved_words: Parser[Token]  = {
        "if" ^^^ IfToken |
        "else" ^^^ ElseToken |
        "while" ^^^ WhileToken |
        "print" ^^^ PrintToken
    }
    
    def reserved_symbols: Parser[Token] = {
        "(" ^^^ LeftParenToken |
        ")" ^^^ RightParenToken |
        "{" ^^^ LeftCurlyBraceToken |
        "}" ^^^ RightCurlyBraceToken
    }
    
    def types: Parser[Token] = {
        "int" ^^^ IntToken |
        "bool" ^^^ BoolToken |
        "string" ^^^ StringToken
    }
    
    def binary_ops: Parser[Token] = {
        "+" ^^^ PlusToken |
        "-" ^^^ MinusToken |
        "<" ^^^ LessThanToken |
        ">" ^^^ GreaterThanToken |
        "==" ^^^ ExactlyEqualsToken |
        "!=" ^^^ NotEqualsToken
    }
    
    def unary_ops: Parser[Token] = {
        "!" ^^^ NotUnaryToken |
        "--" ^^^ MinusUnaryToken
    }
    
    def expressions: Parser[Token] = {
        "true" ^^^ TrueBooleanLiteralToken |
        "false" ^^^ FalseBooleanLiteralToken |
        "[0-9]+".r ^^ { case num => IntegerLiteralToken(num) } |
        """["][^"]*["]""".r ^^ { case string => StringLiteralToken(string) } |
        "[a-zA-Z_][a-zA-Z0-9_]*".r ^^ { case string => VariableToken(string) }
    }
    
    def tokens: Parser[List[Token]] = {
        phrase(rep(reserved_words | reserved_symbols | types | binary_ops | unary_ops | expressions))
    }
    
    def apply(code: String): Seq[Token] = {
        parse(tokens, code) match {
            case Success(result, _) => result
            case Failure(msg, _) => throw new TokenizerException(msg)
            case Error(msg, _) => throw new TokenizerException(msg)
        }
    }
}