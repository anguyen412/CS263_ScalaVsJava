package com.simple_lang.parser;

import java.util.List;

public class BlockStmt implements Stmt {
    public final List<Stmt> stmts;
    
    public BlockStmt(final List<Stmt> stmts) {
        this.stmts = stmts;
    }
    
    public String toString() {
        return "BlockStmt(" + stmts.toString() + ")";
    }
    
    public boolean equals(final Object other) {
        if (other instanceof BlockStmt) {
            final BlockStmt otherStmt = (BlockStmt)other;
            return stmts.equals(otherStmt.stmts);
        } else {
            return false;
        }
    }
}