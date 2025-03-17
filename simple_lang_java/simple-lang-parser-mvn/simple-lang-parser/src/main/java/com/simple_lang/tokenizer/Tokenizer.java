package simple_lang.tokenizer;

import java.util.*;
import java.util.regex.*;

public class Tokenizer {

    // Reserved words
    private static Token reservedWords(String code) {
        if (code.startsWith("if")) return new IfToken();
        if (code.startsWith("else")) return new ElseToken();
        if (code.startsWith("while")) return new WhileToken();
        if (code.startsWith("print")) return new PrintToken();
        return null;
    }

    // Reserved symbols
    private static Token reservedSymbols(String code) {
        if (code.startsWith("(")) return new LeftParenToken();
        if (code.startsWith(")")) return new RightParenToken();
        if (code.startsWith("{")) return new LeftCurlyBraceToken();
        if (code.startsWith("}")) return new RightCurlyBraceToken();
        if (code.startsWith("=")) return new AssignmentToken();
        if (code.startsWith(";")) return new SemicolonToken();
        return null;
    }

    // Types
    private static Token types(String code) {
        if (code.startsWith("int")) return new IntToken();
        if (code.startsWith("bool")) return new BoolToken();
        if (code.startsWith("string")) return new StringToken();
        return null;
    }

    // Binary operators
    private static Token binaryOps(String code) {
        if (code.startsWith("+")) return new PlusToken();
        if (code.startsWith("-")) return new MinusToken();
        if (code.startsWith("<")) return new LessThanToken();
        if (code.startsWith(">")) return new GreaterThanToken();
        if (code.startsWith("==")) return new ExactlyEqualsToken();
        if (code.startsWith("!=")) return new NotEqualsToken();
        return null;
    }

    // Unary operators
    private static Token unaryOps(String code) {
        if (code.startsWith("!")) return new NotUnaryToken();
        if (code.startsWith("--")) return new MinusUnaryToken();
        return null;
    }

     // Expressions
    private static Token expressions(String code) {
        //System.out.println("Current exp in expressions: " + code);
        if (code.startsWith("true")) return new TrueBooleanLiteralToken();
        if (code.startsWith("false")) return new FalseBooleanLiteralToken();
        
        Pattern p = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Matcher m = p.matcher(code);
        if (m.find()) {
            String s = m.group();
            //System.out.println("We matched: " + s);
            return new VariableToken(s);
        }
        
        Pattern p1 = Pattern.compile("[0-9]+");
        Matcher m1 = p1.matcher(code);
        if (m1.find()) {
            String s1 = m1.group();
            //System.out.println("We matched: " + s1);
            return new IntegerLiteralToken(s1);
        }
        
        Pattern p2 = Pattern.compile("\"[^\"]*\"");
        Matcher m2 = p2.matcher(code);
        if (m2.find()) {
            String s2 = m2.group();
            //System.out.println("We matched: " + s2);
            return new StringLiteralToken(s2);
        }

        //Matcher integerMatcher = Pattern.compile("[0-9]+").matcher(code);
        //if (integerMatcher.find()) return new IntegerLiteralToken(integerMatcher.group());

        //Matcher stringMatcher = Pattern.compile("\"[^\"]*\"").matcher(code);
        //if (stringMatcher.find()) return new StringLiteralToken(stringMatcher.group());

        //Matcher variableMatcher = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*").matcher(code);
        //if (variableMatcher.find()) return new VariableToken(variableMatcher.group());

        return null;
    }

    // Implementation of tokens in Tokenizer.scala
    public static List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        int index = 0;

        // Iterates through the string to generate a list of tokens
        while (index < code.length()) {

            // Match each substring to a token type
            String substring = code.substring(index);

            //System.out.println("Remaining tokens: " + substring);
            //System.out.println("Current list: " + tokens.toString());
            
            Token token = reservedWords(substring);
            if (token != null) {
                //System.out.println("Successfully tokenized this reserved word: " + token.toString());
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = reservedSymbols(substring);
            if (token != null) {
                //System.out.println("Successfully tokenized this symbol: " + token.toString());
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = types(substring);
            if (token != null) {
                //System.out.println("Successfully tokenized this type: " + token.toString());
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = binaryOps(substring);
            if (token != null) {
                //System.out.println("Successfully tokenized this binaryOp: " + token.toString());
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = unaryOps(substring);
            if (token != null) {
                //System.out.println("Successfully tokenized this unaryOp: " + token.toString());
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = expressions(substring);
            if (token != null) {
                //System.out.println("Successfully tokenized this exp: " + token.toString());
                //System.out.println("Token type variable???: " + (token instanceof VariableToken));
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            // Token not found (does something else need to be done?)
            //Yes, we can either throw an exception here... or return a null list
            //Since it's already set up to check for a null list in apply, I'll do that
            //index++; removing
            tokens = null;
            return tokens;
        }
        return tokens;
    }

    // Implementation of apply method in Tokenizer.scala
    public static List<Token> apply(String code) throws TokenizerException {
        String noWhitespaceCode = code.replaceAll("\\s+", "");
        //System.out.println(noWhitespaceCode);
        List<Token> tokens = tokenize(noWhitespaceCode);
        if (tokens == null) {
            throw new TokenizerException("Failure");
        }
        return tokens;
    }
    
}
