package simple_lang.tokenizer;

public class RightParenToken implements Token {
    public int getLength() { return 1; }
    
    public String toString() {
        return ")";
    }
    
    public boolean equals(final Object other) {
        return other instanceof RightParenToken;
    }
}