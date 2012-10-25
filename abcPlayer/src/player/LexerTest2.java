package player;

import java.io.IOException;

import org.junit.Test;

public class LexerTest2 {
    @Test
    public void TestPiece1() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("piece1.abc");
        System.out.println(l.toString());
    }
    
    @Test
    public void TestPiece2() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("piece2.abc");
        System.out.println(l.toString());
    }

    @Test
    public void TestPieceFur_Elise() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("fur_elise.abc");
        System.out.println(l.toString());
    }
    
    @Test
    public void TestInvention() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("invention.abc");
        System.out.println(l.toString());
    }
    
    @Test
    public void TestLittle_Night_Music() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("little_night_music.abc");
        System.out.println(l.toString());
    }
    
    @Test
    public void TestPaddy() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("paddy.abc");
        System.out.println(l.toString());
    }
    
    @Test
    public void TestPrelude() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("prelude.abc");
        System.out.println(l.toString());
    }
    
}