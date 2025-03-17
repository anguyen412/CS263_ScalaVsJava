package com.simple_lang.parser;

public class VariableExp implements Exp {
    public final String name;
    
    public VariableExp(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return "VariableExp(" + name + ")";
    }
    
    public boolean equals(final Object other) {
        return (other instanceof VariableExp && name.equals(((VariableExp)other).name));
    }
}