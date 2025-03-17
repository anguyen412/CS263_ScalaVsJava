package simple_lang.tokenizer;

public class SemicolonToken implements Token {
    public int getLength() { return 1; }
    
    public String toString() {
        return ";";
    }
    
    public boolean equals(final Object object) {
        return object instanceof SemicolonToken;
    }
}