package simple_lang.tokenizer;

public class IfToken implements Token {
    public int getLength() { return 2; }
    
    public String toString() {
        return "if";
    }
}