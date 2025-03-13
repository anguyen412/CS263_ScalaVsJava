package simple_lang.tokenizer;

public class WhileToken implements Token {
    public int getLength() { return 5; }
    
    public String toString() {
        return "while";
    }
}