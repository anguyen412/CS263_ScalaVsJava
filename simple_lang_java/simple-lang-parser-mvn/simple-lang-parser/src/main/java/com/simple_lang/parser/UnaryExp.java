package com.simple_lang.parser;

public class UnaryExp implements Exp {
    public final UnaryOp op;
    public final Exp exp;
    
    public UnaryExp(final UnaryOp op, final Exp exp) {
        this.op = op;
        this.exp = exp;
    }
    
    public String toString() {
        return "UnaryExp(" + op.toString() + "," + exp.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof UnaryExp) {
            final UnaryExp otherExp = (UnaryExp)other;
            return (op.equals(otherExp.op) && exp.equals(otherExp.exp));
        } else {
            return false;
        }
    }
}