package com.simple_lang.parser;

public class WhileStmt implements Stmt {
    public final Exp predicate;
    final Stmt stmt;
    
    public WhileStmt(final Exp predicate, final Stmt stmt) {
        this.predicate = predicate;
        this.stmt = stmt;
    }
    
    public String toString() {
        return "WhileStmt(" + predicate.toString() + "," + stmt.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof WhileStmt) {
            final WhileStmt otherStmt = (WhileStmt)other;
            return (predicate.equals(otherStmt.predicate) && stmt.equals(otherStmt.stmt));
        } else {
            return false;
        }
    }
}