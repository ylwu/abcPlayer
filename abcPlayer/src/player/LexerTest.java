package player;

import java.io.IOException;

import org.junit.Test;

public class LexerTest {
    
    public void TestPiece1() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("/Users/weidadianli/Desktop/6.005/projects/Project_1/abcPlayer/sample_abc/piece1.abc");
        System.out.println(l.toString());
    }
    
    
    public void TestPiece2() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("/Users/weidadianli/Desktop/6.005/projects/Project_1/abcPlayer/sample_abc/piece2.abc");
        System.out.println(l.toString());
    }

    @Test
    public void TestPieceFur_Elise() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("/Users/weidadianli/Desktop/6.005/projects/Project_1/abcPlayer/sample_abc/prelude.abc");
        System.out.println(l.toString());
    }
}
