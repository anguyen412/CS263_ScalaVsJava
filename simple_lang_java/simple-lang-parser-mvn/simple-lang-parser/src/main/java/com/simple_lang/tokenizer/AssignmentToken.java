package simple_lang.tokenizer;

public class AssignmentToken implements Token {
    public int getLength() { return 1; }
    
    public String toString() {
        return "=";
    }
}