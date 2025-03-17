package com.simple_lang.parser;

public class BinOpExp implements Exp {
    public final Exp left;
    public final BinOp op;
    public final Exp right;
    
    public BinOpExp(final Exp left, final BinOp op, final Exp right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }
    
    public String toString() {
        return "BinOpExp(" + left.toString() + "," + op.toString() + "," + right.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof BinOpExp) {
            final BinOpExp otherExp = (BinOpExp)other;
            return (left.equals(otherExp.left) && op.equals(otherExp.op) && right.equals(otherExp.right));
        } else {
            return false;
        }
    }
}