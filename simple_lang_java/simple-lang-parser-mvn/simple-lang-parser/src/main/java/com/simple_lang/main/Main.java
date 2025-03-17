package com.simple_lang.main;

import simple_lang.tokenizer.*;
import com.simple_lang.parser.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws TokenizerException, ParseException {
        String code = "print(2 + 2);";
        //code = "{}";
        //code = "abc = 1;";
        List<Token> tokens = Tokenizer.apply(code);
        
        //for (Token token : tokens) {
        //    System.out.print(token.toString());
        //}
        
        Parser p = new Parser(tokens);
        Program ast = p.parseProgram();
        System.out.println(ast.toString());
    }
}
