package simple_lang.tokenizer;

interface Token {
    int getLength(); // Added this for the manual tokenizer 
}

// types
class IntToken implements Token {
    public int getLength() {return 3;}
}
class BoolToken implements Token {
    public int getLength() {return 4;}
}
class StringToken implements Token {
    public int getLength() {return 6;}
}

// binary operators
class PlusToken implements Token {
    public int getLength() {return 1;}
}
class MinusToken implements Token {
    public int getLength() {return 1;}
}
class LessThanToken implements Token {
    public int getLength() {return 1;} 
}
class GreaterThanToken implements Token {
    public int getLength() {return 1;} 
}
class ExactlyEqualsToken implements Token {
    public int getLength() {return 2;} 
}
class NotEqualsToken implements Token {
    public int getLength() {return 2;} 
}

// unary operators
class NotUnaryToken implements Token {
    public int getLength() {return 1;} 
}
class MinusUnaryToken implements Token {
    public int getLength() {return 2;}
}

// expressions
class VariableToken implements Token {
    private final String value;
    public VariableToken(String value) {this.value = value;}
    public String getValue() {return value;}
    public int getLength() {return value.length();}
}
class IntegerLiteralToken implements Token {
    private final String value;
    public IntegerLiteralToken(String value) {this.value = value;}
    public String getValue() {return value;}
    public int getLength() {return value.length();}
}
class TrueBooleanLiteralToken implements Token {
    public int getLength() {return 4;}
}
class FalseBooleanLiteralToken implements Token {
    public int getLength() {return 5;}
}
class StringLiteralToken implements Token {
    private final String value;
    public StringLiteralToken(String value) {this.value = value;}
    public String getValue() {return value;}
    public int getLength() {return value.length();}
}

// other reserved words and symbols
class IfToken implements Token {
    public int getLength() {return 2;}
}
class ElseToken implements Token {
    public int getLength() {return 4;} 
}
class WhileToken implements Token {
    public int getLength() {return 5;} 
}
class PrintToken implements Token {
    public int getLength() {return 5;}  
}
class LeftParenToken implements Token {
    public int getLength() {return 1;}  
}
class RightParenToken implements Token {
    public int getLength() {return 1;}  
}
class LeftCurlyBraceToken implements Token {
    public int getLength() {return 1;}  
}
class RightCurlyBraceToken implements Token {
    public int getLength() {return 1;} 
}
class AssignmentToken implements Token {
    public int getLength() {return 1;} 
}
class SemicolonToken implements Token {
    public int getLength() {return 1;} 
}
