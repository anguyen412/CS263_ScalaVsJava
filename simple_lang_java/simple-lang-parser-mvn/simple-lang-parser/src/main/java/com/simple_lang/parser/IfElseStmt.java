package com.simple_lang.parser;

public class IfElseStmt implements Stmt {
    public final Exp predicate;
    public final Stmt trueBranch;
    public final Stmt falseBranch;
    
    public IfElseStmt(final Exp predicate, final Stmt trueBranch, final Stmt falseBranch) {
        this.predicate = predicate;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
    
    public String toString() {
        return "IfElseStmt(" + predicate.toString() + "," + trueBranch.toString() + "," + falseBranch.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof IfElseStmt) {
            final IfElseStmt otherStmt = (IfElseStmt)other;
            return (predicate.equals(otherStmt.predicate) && trueBranch.equals(otherStmt.trueBranch) && falseBranch.equals(otherStmt.falseBranch));
        } else {
            return false;
        }
    }
}