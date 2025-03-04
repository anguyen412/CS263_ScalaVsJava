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
    
    def variable: Parser[VariableToken] = { //we can change everything to lazy vals later as an optimization
        accept("variable", {case id @ VariableToken(name) => id})
    }
    
    def integer: Parser[IntegerLiteralToken] = {
        accept("integer", {case id @ IntegerLiteralToken(value) => id})
    }
    
    def string: Parser[StringLiteralToken] = {
        accept("string", {case id @ StringLiteralToken(value) => id})
    }
    
    def typ: Parser[Type] = {
        IntToken ^^^ IntType |
        BoolToken ^^^ BoolType |
        StringToken ^^^ StringType
    }
    
    def additive_op: Parser[BinOp] = {
        PlusToken ^^^ PlusBinOp |
        MinusToken ^^^ MinusBinOp
    }
    
    def comparison_op: Parser[BinOp] = {
        LessThanToken ^^^ LessThanBinOp |
        GreaterThanToken ^^^ GreaterThanBinOp
    }
    
    def equals_op: Parser[BinOp] = {
        ExactlyEqualsToken ^^^ ExactlyEqualsBinOp |
        NotEqualsToken ^^^ NotEqualsBinOp
    }
    
    def unary_op: Parser[UnaryOp] = {
        NotUnaryToken ^^^ NotUnaryOp |
        MinusUnaryToken ^^^ MinusUnaryOp
    }
    
    def exp_maker(prefix: Exp, list: List[(BinOp ~ Exp)]): Exp = {
        list match {
            case Nil => prefix
            case op ~ exp :: tail => BinOpExp(prefix, op, exp_maker(exp, tail))
        }
    }
    
    def primary_exp: Parser[Exp] = {
        variable ^^ { case value => VariableExp(value.value) } |
        integer ^^ { case value => IntegerLiteralExp(value.value) } |
        string ^^ { case value => StringLiteralExp(value.value) } |
        TrueBooleanLiteralToken ^^^ TrueLiteralExp |
        FalseBooleanLiteralToken ^^^ FalseLiteralExp
    }
    
    def additive_exp: Parser[Exp] = {
        primary_exp ~ rep(additive_op ~ primary_exp) ^^ { case exp ~ list => exp_maker(exp, list) }
    }
    
    def comparison_exp: Parser[Exp] = {
        additive_exp ~ rep(comparison_op ~ additive_exp) ^^ { case exp ~ list => exp_maker(exp, list) }
    }
    
    def equals_exp: Parser[Exp] = {
        comparison_exp ~ rep(equals_op ~ comparison_exp) ^^ { case exp ~ list => exp_maker(exp, list) }
    }
    
    def unary_exp: Parser[Exp] = {
        unary_op ~ equals_exp ^^ { case op ~ exp => UnOpExp(op, exp) } |
        equals_exp ^^ { case exp => exp }
    }
    
    def exp: Parser[Exp] = {
        unary_exp ^^ { case exp => exp }      
    }

    def stmt: Parser[Stmt] = {
        typ ~ variable ~ AssignmentToken ~ exp ~ SemicolonToken ^^ { case annotation ~ var_name ~ _ ~ exp ~ _ => VariableDeclarationStmt(annotation, VariableExp(var_name.value), exp) } |
        variable ~ AssignmentToken ~ exp ~ SemicolonToken ^^ { case var_name ~ _ ~ exp ~ _ => AssignmentStmt(VariableExp(var_name.value), exp) } |
        IfToken ~ LeftParenToken ~ exp ~ RightParenToken ~ stmt ~ ElseToken ~ stmt ^^ { 
            case _ ~ _ ~ exp ~ _ ~ stmt1 ~ _ ~ stmt2 => IfElseStmt(exp, stmt1, stmt2)
        } |
        WhileToken ~ LeftParenToken ~ exp ~ RightParenToken ~ stmt ^^ { case _ ~ _ ~ exp ~ _ ~ stmt => WhileStmt(exp, stmt) } |
        PrintToken ~ LeftParenToken ~ exp ~ RightParenToken ~ SemicolonToken ^^ { case _ ~ _ ~ exp ~ _  ~ _ => PrintStmt(exp) } |
        LeftCurlyBraceToken ~ rep(stmt) ~ RightCurlyBraceToken ^^ { case _ ~ stmts ~ _ => BlockStmt(stmts) }
    }
    
    def program: Parser[Program] = {
        rep(stmt) ^^ { case stmts => Program(stmts) }
    }
    
    def phrase_program: Parser[Program] = {
        phrase(program)
    }
    
    def apply(tokens: Seq[Token]): Program = {
        val reader = new TokenReader(tokens)
        phrase_program(reader) match {
            case Success(result, _) => result
            case Failure(message, _) => throw new ParserException("Parsing Failure")
            case Error(message, _) => throw new ParserException("Parsing Error")
        }
    }
}