package player;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * Testing for edge cases including:
 * -containing the following elements, legal and illegal cases:
 * --single characters: in possible selections/ not
 * --numbers: should be all legal
 * --accidentals: attached to notes/ not
 * --chords: space issue
 * --duplets/triplets: space issue
 * -header lines shorter than 2 chars (illegal)
 * -missing required header parts (illegal)
 * -absent/one/excessive spaces (case specific)
 *
 */
public class LexerTest {
    @Test
    public void TestLegalChars() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("piece1.abc");
    }
    
    @Test
    public void TestIllegalChars() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestLegalNums() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestIllegalNums() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
        System.out.println(l.toString());
    }

    @Test
    public void TestLegalAccs() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestIllegalAccs() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestLegalChords() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestIllegalChords() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestEmptyLines() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("empty_lines.abc");
    }
    
    @Test
    public void TestComments() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("comment.abc");
        System.out.println(l.toString());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void TestInvalidMissingK1() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("missingk_1.abc");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestInvalidMissingK2() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("missingk_2.abc");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestInvalidMissingK3() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("missingk_3.abc");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestInvalidMissingT() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("missingT.abc");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestInvalidMissingX() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("missingX.abc");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestUnRecognizedCharacter() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("bad_char.abc");
    }

}
