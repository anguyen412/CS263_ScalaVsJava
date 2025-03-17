package simple_lang.tokenizer;

public class ElseToken implements Token {
    public int getLength() { return 4; }
    
    public String toString() {
        return "else";
    }
    
    public boolean equals(final Object other) {
        return other instanceof ElseToken;
    }
}