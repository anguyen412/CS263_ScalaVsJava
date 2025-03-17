package com.simple_lang.parser;

import simple_lang.tokenizer.*;

import java.util.List;
import java.util.ArrayList;

public class Parser {
    private final List<Token> tokens;
    
    public Parser(final List<Token> tokens) {
        this.tokens = tokens;
    }
    
    public Token getToken(final int position) throws ParseException {
        if (position >= 0 && position < tokens.size()) {
            return tokens.get(position);
        } else {
            throw new ParseException("Invalid token position: " + position);
        }
    }
    
    public void assertTokenHereIs(final int position, final Token expected) throws ParseException {
        final Token received = getToken(position);
        if (!expected.equals(received)) {
            throw new ParseException("expected: " + expected + " but received: " + received);
        }
    }
    
    public ParseResult<Type> parseType(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof IntToken) {
            return new ParseResult<Type>(new IntType(), position + 1);
        } else if (token instanceof BoolToken) {
            return new ParseResult<Type>(new BoolType(), position + 1);
        } else if (token instanceof StringToken) {
            return new ParseResult<Type>(new StringType(), position + 1);
        } else {
            throw new ParseException("Could not parse type.");
        }
    }

    public ParseResult<Exp> parsePrimaryExp(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof VariableToken) {
            final VariableToken varToken = (VariableToken)token;
            return new ParseResult<Exp>(new VariableExp(varToken.name), position + 1);
        } else if (token instanceof IntegerLiteralToken) {
            final IntegerLiteralToken intToken = (IntegerLiteralToken)token;
            return new ParseResult<Exp>(new IntegerExp(intToken.value), position + 1);
        } else if (token instanceof TrueBooleanLiteralToken) {
            return new ParseResult<Exp>(new TrueExp(), position + 1);
        } else if (token instanceof FalseBooleanLiteralToken) {
            return new ParseResult<Exp>(new FalseExp(), position + 1);
        } else if (token instanceof StringLiteralToken) {
            final StringLiteralToken stringToken = (StringLiteralToken)token;
            return new ParseResult<Exp>(new StringExp(stringToken.value), position + 1);
        } else {
            throw new ParseException("Failed parsing primary expression.");
        }
    }
    
    public ParseResult<BinOp> parseAdditiveOp(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof PlusToken) {
            return new ParseResult<BinOp>(new PlusBinOp(), position + 1);
        } else if (token instanceof MinusToken) {
            return new ParseResult<BinOp>(new MinusBinOp(), position + 1);
        } else {
            throw new ParseException("Failed when parsing additive op");
        }
    }
    
    public ParseResult<Exp> parseAdditiveExp(final int position) throws ParseException {
        ParseResult<Exp> current = parsePrimaryExp(position);
        final Token token = getToken(current.position);
        if (token instanceof PlusToken || token instanceof MinusToken) {
            ParseResult<BinOp> op = parseAdditiveOp(current.position);
            ParseResult<Exp> rhs = parseExp(op.position);
            return new ParseResult<Exp>(new BinOpExp(current.result, op.result, rhs.result), rhs.position);
        } else {
            return current;
        }
    }
    
    public ParseResult<BinOp> parseComparisonOp(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof LessThanToken) {
            return new ParseResult<BinOp>(new LessThanBinOp(), position + 1);
        } else if (token instanceof GreaterThanToken) {
            return new ParseResult<BinOp>(new GreaterThanBinOp(), position + 1);
        } else {
            throw new ParseException("Failed when parsing comparison op.");
        }
    }
    
    public ParseResult<Exp> parseComparisonExp(final int position) throws ParseException {
        ParseResult<Exp> current = parseAdditiveExp(position);
        final Token token = getToken(current.position);
        if (token instanceof LessThanToken || token instanceof GreaterThanToken) {
            ParseResult<BinOp> op = parseComparisonOp(current.position);
            ParseResult<Exp> rhs = parseExp(op.position);
            return new ParseResult<Exp>(new BinOpExp(current.result, op.result, rhs.result), rhs.position);
        } else {
            return current;
        }
    }
    
    public ParseResult<BinOp> parseEqualsOp(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof ExactlyEqualsToken) {
            return new ParseResult<BinOp>(new ExactlyEqualsBinOp(), position + 1);
        } else if (token instanceof NotEqualsToken) {
            return new ParseResult<BinOp>(new NotEqualsBinOp(), position + 1);
        } else {
            throw new ParseException("Failed parsing equals op.");
        }
    }
    
    public ParseResult<Exp> parseEqualsExp(final int position) throws ParseException {
        ParseResult<Exp> current = parseComparisonExp(position);
        final Token token = getToken(current.position);
        if (token instanceof ExactlyEqualsToken || token instanceof NotEqualsToken) {
            ParseResult<BinOp> op = parseEqualsOp(current.position);
            ParseResult<Exp> rhs = parseExp(op.position);
            return new ParseResult<Exp>(new BinOpExp(current.result, op.result, rhs.result), rhs.position);
        } else {
            return current;
        }
    }
    
    public ParseResult<UnaryOp> parseUnaryOp(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof NotUnaryToken) {
            return new ParseResult<UnaryOp>(new NotUnaryOp(), position + 1);
        } else if (token instanceof MinusUnaryToken) {
            return new ParseResult<UnaryOp>(new NegationUnaryOp(), position + 1);
        } else {
            throw new ParseException("Failed parsing unary op.");
        }
    }
    
    public ParseResult<Exp> parseUnaryExp(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof NotUnaryToken || token instanceof MinusUnaryToken) {
            final ParseResult<UnaryOp> op = parseUnaryOp(position);
            final ParseResult<Exp> equalsExp = parseEqualsExp(op.position);
            return new ParseResult<Exp>(new UnaryExp(op.result, equalsExp.result), equalsExp.position);
        } else {
            return (parseEqualsExp(position));
        }
    }
    
    public ParseResult<Exp> parseExp(final int position) throws ParseException {
        return (parseUnaryExp(position));
        
    }
    
    public ParseResult<Stmt> parseVarDecStmt(final int position) throws ParseException {
        final ParseResult<Type> type = parseType(position);
        final Token token1 = getToken(type.position);
        if (token1 instanceof VariableToken) {
            final String varName = ((VariableToken)token1).name;
            assertTokenHereIs(type.position + 1, (new AssignmentToken()));
            final ParseResult<Exp> exp = parseExp(type.position + 2);
            assertTokenHereIs(exp.position, (new SemicolonToken()));
            return new ParseResult<Stmt>(new VarDecStmt(type.result, new VariableExp(varName), exp.result), exp.position + 1);
        } else {
            throw new ParseException("Could not parse variable declaration stmt");
        }
    }
    
    public ParseResult<Stmt> parseAssignmentStmt(final int position) throws ParseException {
        final Token token = getToken(position);
        final VariableToken varToken = (VariableToken)token;
        assertTokenHereIs(position + 1, new AssignmentToken());
        final ParseResult<Exp> exp = parseExp(position + 2);
        assertTokenHereIs(exp.position, new SemicolonToken());
        return new ParseResult<Stmt>(new AssignmentStmt(new VariableExp(varToken.name), exp.result), exp.position + 1);
    }
    
    public ParseResult<Stmt> parseIfElseStmt(final int position) throws ParseException {
        final ParseResult<Exp> predicate = parseExp(position);
        assertTokenHereIs(predicate.position, new RightParenToken());
        final ParseResult<Stmt> trueBranch = parseStmt(predicate.position + 1);
        assertTokenHereIs(trueBranch.position, new ElseToken());
        final ParseResult<Stmt> falseBranch = parseStmt(trueBranch.position + 1);
        return new ParseResult<Stmt>(new IfElseStmt(predicate.result, trueBranch.result, falseBranch.result), falseBranch.position);
    }
    
    public ParseResult<Stmt> parseWhileStmt(final int position) throws ParseException {
        final ParseResult<Exp> predicate = parseExp(position);
        assertTokenHereIs(predicate.position, new RightParenToken());
        final ParseResult<Stmt> body = parseStmt(predicate.position + 1);
        return new ParseResult<Stmt>(new WhileStmt(predicate.result, body.result), body.position);
    }
    
    public ParseResult<Stmt> parsePrintStmt(final int position) throws ParseException {
        final ParseResult<Exp> exp = parseExp(position);
        assertTokenHereIs(exp.position, new RightParenToken());
        assertTokenHereIs(exp.position + 1, new SemicolonToken());
        return new ParseResult<Stmt>(new PrintStmt(exp.result), exp.position + 2);
    }
    
    public ParseResult<Stmt> parseBlockStmt(final int position) throws ParseException {
        ParseResult<Stmt> stmt;   //need this so i have access to positional info outside of loop
        Token nextToken;    //same as above
        List<Stmt> stmts = new ArrayList<>();
        final Token token = getToken(position);
        if (token instanceof RightCurlyBraceToken) {
            return new ParseResult<Stmt>(new BlockStmt(stmts), position + 1);
        } else {
            do {
                stmt = parseStmt(position + 1);
                stmts.add(stmt.result);
                nextToken = getToken(stmt.position);
            } while (!(nextToken instanceof RightCurlyBraceToken));
            return new ParseResult<Stmt>(new BlockStmt(stmts), stmt.position + 1);
        }
    }
    
    public ParseResult<Stmt> parseStmt(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof IntToken || token instanceof BoolToken || token instanceof StringToken) {
            return parseVarDecStmt(position);
        } else if (token instanceof VariableToken) {
            return parseAssignmentStmt(position);
        } else if (token instanceof IfToken) {
            return parseIfElseStmt(position + 2); //skipping the if and the left paren
        } else if (token instanceof WhileToken) {
            return parseWhileStmt(position + 2); //skipping the while and the left paren
        } else if (token instanceof PrintToken) {
            return parsePrintStmt(position + 2); //skipping the print and the left paren
        } else if (token instanceof LeftCurlyBraceToken) {
            return parseBlockStmt(position + 1); //skipping the left curly brace
        } else {
            throw new ParseException("Failed when parsing stmt");
        }
    }
    
    public ParseResult<Program> parseProgram(final int position) throws ParseException {
        List<Stmt> stmts = new ArrayList<Stmt>();
        boolean shouldRunStmts = true;
        int newPosition = position;
        while(shouldRunStmts) {
            try {
                final ParseResult<Stmt> stmt = parseStmt(newPosition);
                //System.out.println("Just parsed this stmt: " + stmt.result.toString());
                stmts.add(stmt.result);
                //System.out.println("The current list of stmts is: " + stmts.toString());
                newPosition = stmt.position;
            } catch (final ParseException e) {
                shouldRunStmts = false;
            }
        }
        return new ParseResult<Program>(new Program(stmts), newPosition);
    }
    
    public Program parseProgram() throws ParseException {
        final ParseResult<Program> program = parseProgram(0);
        if (program.position == tokens.size()) {
            //System.out.println("This is the program I'm gonna return: " + program.result.toString());
            return program.result;
        } else {
            throw new ParseException("Did not parse all tokens, current parse: " + program.result.toString());
        }
    }
}