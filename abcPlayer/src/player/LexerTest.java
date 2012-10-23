package player;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
    public void TestLegalSingles() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_single.abc");
        assertEquals("Header: ",
                l.toString());
    }
    
    @Test
    public void TestLegalNumbers() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_numbers.abc");
        assertEquals("Header: ",
                l.toString());
    }
    
    @Test
    public void TestLegalChords() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_accidentals.abc");
        assertEquals("Header: ",
                l.toString());
    }
    @Test
    public void TestLegalAccidentals() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_accidentals.abc");
        assertEquals("Header: ",
                l.toString());
    }
    
    @Test
    public void TestLegalBlahplets() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_plets.abc");
        assertEquals("Header: ",
                l.toString());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestIllegalSingles() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_single.abc");
    }
    
 // Not tested. No illegal number case for lexer
//  @Test(expected = IllegalArgumentException.class)
    public void TestIllegalNumbers() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_number.abc");
    }
    
 // Not tested. No illegal chords case for lexer
//  @Test(expected = IllegalArgumentException.class)
    public void TestIllegalChords() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_chords.abc");
    }
    
 // Not tested. No illegal accidentals case for lexer
//  @Test(expected = IllegalArgumentException.class)
    public void TestIllegalAccidentals() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_accidentals.abc");
    }
    
    @Test
    public void TestIllegalBlahplets() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_plets.abc");
        assertFalse(l.toString().equals(""));
    }
    
    @Test
    public void TestComments() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_comment.abc");
        assertEquals("Header: ", l.toString());
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
