package com.simple_lang.parser;

public class AssignmentStmt implements Stmt {
    public final VariableExp variable;
    public final Exp exp;
    
    public AssignmentStmt(final VariableExp variable, final Exp exp) {
        this.variable = variable;
        this.exp = exp;
    }
    
    public String toString() {
        return "AssignmentStmt(" + variable.toString() + "," + exp.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof AssignmentStmt) {
            final AssignmentStmt otherStmt = (AssignmentStmt)other;
            return (variable.equals(otherStmt.variable) && exp.equals(otherStmt.exp));
        } else {
            return false;
        }
    }
}