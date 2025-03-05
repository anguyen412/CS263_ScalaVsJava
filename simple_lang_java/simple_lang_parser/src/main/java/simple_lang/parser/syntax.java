package simple_lang.parser;
import java.util.List;

// Types
interface Type {}
enum BasicType implements Type {
    INT, BOOL, STRING;
}

// UnaryOp
interface UnaryOp {}
enum UnaryOperator implements UnaryOp {
    NOT, MINUS;
}

// BinaryOp
interface BinOp {}
enum BinaryOperator implements BinOp {
    PLUS, MINUS, LESS_THAN, GREATER_THAN, EXACTLY_EQUALS, NOT_EQUALS;
}

// Expressions
interface Exp {}

class VariableExp implements Exp {
    private final String value;
    public VariableExp(String value) {this.value = value;}
    public String getValue() {return value;}
}

class IntegerLiteralExp implements Exp {
    private final String value;
    public IntegerLiteralExp(String value) {this.value = value;}
    public String getValue() {return value;}
}

class StringLiteralExp implements Exp {
    private final String value;
    public StringLiteralExp(String value) {this.value = value;}
    public String getValue() {return value;}
}

class UnOpExp implements Exp {
    private final UnaryOp op;
    private final Exp exp;

    public UnOpExp(UnaryOp op, Exp exp) {
        this.op = op;
        this.exp = exp;
    }
    public UnaryOp getOp() {return op;}
    public Exp getExp() {return exp;}
}

class BinOpExp implements Exp {
    private final Exp exp1;
    private final BinOp op;
    private final Exp exp2;
    public BinOpExp(Exp exp1, BinOp op, Exp exp2) {
        this.exp1 = exp1;
        this.op = op;
        this.exp2 = exp2;
    }
    public Exp getExp1() {return exp1;}
    public BinOp getOp() {return op;}
    public Exp getExp2() {return exp2;}
}

// Statements
interface Stmt {}

class VariableDeclarationStmt implements Stmt {
    private final Type typ;
    private final VariableExp variable;
    private final Exp exp;
    public VariableDeclarationStmt(Type typ, VariableExp variable, Exp exp) {
        this.typ = typ;
        this.variable = variable;
        this.exp = exp;
    }
    public Type getType() {return typ;}
    public VariableExp getVariable() {return variable;}
    public Exp getExp() {return exp;}
}

class AssignmentStmt implements Stmt {
    private final VariableExp variable;
    private final Exp exp;
    public AssignmentStmt(VariableExp variable, Exp exp) {
        this.variable = variable;
        this.exp = exp;
    }
    public VariableExp getVariable() {return variable;}
    public Exp getExp() {return exp;}
}

class IfElseStmt implements Stmt {
    private final Exp predicate;
    private final Stmt trueBranch;
    private final Stmt falseBranch;
    public IfElseStmt(Exp predicate, Stmt trueBranch, Stmt falseBranch) {
        this.predicate = predicate;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
    public Exp getPredicate() {return predicate;}
    public Stmt getTrueBranch() {return trueBranch;}
    public Stmt getFalseBranch() {return falseBranch;}
}

class WhileStmt implements Stmt {
    private final Exp predicate;
    private final Stmt stmt;
    public WhileStmt(Exp predicate, Stmt stmt) {
        this.predicate = predicate;
        this.stmt = stmt;
    }
    public Exp getPredicate() {return predicate;}
    public Stmt getStmt() {return stmt;}
}

class PrintStmt implements Stmt {
    private final Exp exp;
    public PrintStmt(Exp exp) {this.exp = exp;}
    public Exp getExp() {return exp;}
}

class BlockStmt implements Stmt {
    private final List<Stmt> stmts;
    public BlockStmt(List<Stmt> stmts) {this.stmts = stmts;}
    public List<Stmt> getStmts() {return stmts;}
}

// Program
class Program {
    private final List<Stmt> stmts;
    public Program(List<Stmt> stmts) {this.stmts = stmts;}
    public List<Stmt> getStmts() {return stmts;}
}
