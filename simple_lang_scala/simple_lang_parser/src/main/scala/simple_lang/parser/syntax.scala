package simple_lang.parser

sealed trait Type
case object IntType extends Type
case object BoolType extends Type
case object StringType extends Type

sealed trait UnaryOp
case object NotUnaryOp extends UnaryOp
case object MinusUnaryOp extends UnaryOp

sealed trait BinOp
case object PlusBinOp extends BinOp
case object MinusBinOp extends BinOp
case object LessThanBinOp extends BinOp
case object GreaterThanBinOp extends BinOp
case object ExactlyEqualsBinOp extends BinOp
case object NotEqualsBinOp extends BinOp

sealed trait Exp
case class VariableExp(value: String) extends Exp
case class IntegerLiteralExp(value: String) extends Exp
case object TrueLiteralExp extends Exp
case object FalseLiteralExp extends Exp
case class StringLiteralExp(value: String) extends Exp
case class UnOpExp(op: UnaryOp, exp: Exp) extends Exp
case class BinOpExp(exp1: Exp, op: BinOp, exp2: Exp) extends Exp

sealed trait Stmt
case class VariableDeclarationStmt(typ: Type, variable: VariableExp, exp: Exp) extends Stmt
case class AssignmentStmt(variable: VariableExp, exp: Exp) extends Stmt
case class IfElseStmt(predicate: Exp, trueBranch: Stmt, falseBranch: Stmt) extends Stmt
case class WhileStmt(predicate: Exp, stmt: Stmt) extends Stmt
case class PrintStmt(exp: Exp) extends Stmt
case class BlockStmt(stmts: List[Stmt]) extends Stmt

case class Program(stmts: List[Stmt])
