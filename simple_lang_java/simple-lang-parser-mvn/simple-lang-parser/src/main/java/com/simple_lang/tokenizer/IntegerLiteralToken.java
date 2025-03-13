package simple_lang.tokenizer;

public class IntegerLiteralToken implements Token {
    public final String value;
    
    public IntegerLiteralToken(final String value) {
        this.value = value;
    }
    
    public int getLength() {
        return value.length();
    }
    
    public String toString() {
        return value;
    }
}