package com.simple_lang.parser;

import java.util.List;

public class Program {
    public final List<Stmt> stmts;
    
    public Program(final List<Stmt> stmts) {
        this.stmts = stmts;
    }
    
    public String toString() {
        return "Program(" + stmts.toString() + ")";
    }
}