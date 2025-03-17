package com.simple_lang.parser;

public class PrintStmt implements Stmt {
    public final Exp exp;
    
    public PrintStmt(final Exp exp) {
        this.exp = exp;
    }
    
    public String toString() {
        return "PrintExp(" + exp.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof PrintStmt) {
            final PrintStmt otherStmt = (PrintStmt)other;
            return (exp.equals(otherStmt.exp));
        } else {
            return false;
        }
    }
}