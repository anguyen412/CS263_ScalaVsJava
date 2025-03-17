package com.simple_lang.parser;

public class VarDecStmt implements Stmt {
    final Type type;
    final VariableExp variable;
    final Exp exp;
    
    public VarDecStmt(final Type type, final VariableExp variable, final Exp exp) {
        this.type = type;
        this.variable = variable;
        this.exp = exp;
    }
    
    public String toString() {
        return "VarDecStmt(" + type.toString() + "," + variable.toString() + "," + exp.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof VarDecStmt) {
            final VarDecStmt otherStmt = (VarDecStmt)other;
            return (type.equals(otherStmt.type) && variable.equals(otherStmt.variable) && exp.equals(otherStmt.variable));
        } else {
            return false;
        }
    }
}