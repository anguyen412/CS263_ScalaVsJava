package simple_lang.parser

import simple_lang.tokenizer._
import scala.util.parsing.combinator._
import scala.util.parsing.input.{NoPosition, Position, Reader}

class ParserException(message: String) extends Exception(message)

object Parser extends Parsers {
    override type Elem = Token
    
    class TokenReader(tokens: Seq[Token]) extends Reader[Token] {
        override def first: Token = tokens.head
        override def atEnd: Boolean = tokens.isEmpty
        override def pos: Position = NoPosition
        override def rest: Reader[Token] = new TokenReader(tokens.tail)
    }
    
    lazy val variable: Parser[VariableToken] = {
        accept("variable", {case id @ VariableToken(name) => id})
    }
    
    lazy val integer: Parser[IntegerLiteralToken] = {
        accept("integer", {case id @ IntegerLiteralToken(value) => id})
    }
    
    lazy val string: Parser[StringLiteralToken] = {
        accept("string", {case id @ StringLiteralToken(value) => id})
    }
    
    //
    
    def program: Parser[Program] = {
      //  rep(stmt) ^^ { case stmts => Program(stmts) }
    }
    
    def phrase_program: Parser[Program] = {
        phrase(program)
    }
    
    def apply(tokens: Seq[Token]): Program = {
        val reader = new TokenReader(tokens)
        phrase_program(reader) match {
            case Success(result, _) => result
        }
    }
}