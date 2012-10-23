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
 * --accidentals: should be all legal
 * --chords: should be all legal
 * --duplets/triplets: space issue
 * --comments: omit
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
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Single X:1 V:[] $$$ A B C D E F G a b c d e f g | ^ _ = ' , [ ] z 0 9 /",
                l.toString());
    }
    
    @Test
    public void TestLegalNumbers() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_numbers.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Numbers X:2 V:[] $$$ A1 A2/3 A4/ A/5",
                l.toString());
    }
    
    @Test
    public void TestLegalChords() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_chords.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Chords X:3 V:[] $$$ [ABC] [AB]",
                l.toString());
    }
    @Test
    public void TestLegalAccidentals() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_accidentals.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Accidentals X:4 V:[] $$$ ^A _A =A",
                l.toString());
    }
    
    @Test
    public void TestLegalBlahplets() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_plets.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Plets X:5 V:[] $$$ (2AB (3ABC (4ABCD (2A1/2B1/2",
                l.toString());
        assertEquals(l.tokenList.get(0).getType(), Token.Type.DUPLET);
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
    
    @Test(expected = IllegalArgumentException.class)
    public void TestIllegalBlahplets() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_plets.abc");
        assertFalse(l.tokenList.get(0).getType().equals(Token.Type.DUPLET));
    }
    
    @Test
    public void TestComments() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_comment.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Comments X:7 V:[] $$$ A ", l.toString());
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
