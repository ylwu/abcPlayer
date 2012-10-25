package player;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class ParserTest {
    
    @Test
    public void basicTest() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_chords.abc");
        System.out.println(l.toString());
        Parser p = new Parser(l);
        System.out.println(p.toString());
    }
    
    @Test
    public void basicTest2() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_voices.abc");
        System.out.println(l.toString());
        Parser p = new Parser(l);
        System.out.println(p.toString());
    }
    
}