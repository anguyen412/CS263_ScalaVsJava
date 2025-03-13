package simple_lang.tokenizer;

public class StringLiteralToken implements Token {
    public final String value;
    
    public StringLiteralToken(final String value) {
        this.value = value;
    }
    
    public int getLength() {
        return value.length();
    }
    
    public String toString() {
        return value;
    }
}