package simple_lang.tokenizer;

public class ElseToken implements Token {
    public int getLength() { return 4; }
    
    public String toString() {
        return "else";
    }
}