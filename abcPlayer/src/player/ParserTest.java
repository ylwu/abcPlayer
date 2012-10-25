package player;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class ParserTest {
    
    @Test
    public void basicTest() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("parser_legal_simple1.abc");
        Parser p = new Parser(l);
        System.out.println(p.toString());
    }
}
