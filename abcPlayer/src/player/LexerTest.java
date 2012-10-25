package player;

import static org.junit.Assert.*;
import java.io.IOException;
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
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Single X:1 V:[] $$$ A B C D E F G a b c d e f g | ^ _ = ' , [ ] z 0.0 9.0 0.5",
                l.toString());
    }
    
    @Test
    public void TestLegalNumbers() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_numbers.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Numbers X:2 V:[] $$$ A1.0 A0.6666666666666666 A2.0 A0.2",
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
        assertEquals(Token.Type.ACCIDENTAL, l.tokenList.get(0).getType());
        assertEquals(Token.Type.NOTE, l.tokenList.get(1).getType());
        assertEquals(Token.Type.ACCIDENTAL, l.tokenList.get(3).getType());
    }
    
    @Test
    public void TestLegalBlahplets() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_plets.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Plets X:5 V:[] $$$ (2AB (3ABC (4ABCD (2A0.5B0.5",
                l.toString());
        assertEquals(Token.Type.DUPLET, l.tokenList.get(0).getType());
    }
    
    @Test
    public void TestLegalVoices() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_voices.abc");
        assertEquals("Header: C:unknown K:C L:1/8 M:4/4 Q:100 T:Legal Voices X:8 V:[1, 2, A] $$$ 1A B C D2A B C DAA B C D",
                l.toString());
        assertEquals(Token.Type.VOICE, l.tokenList.get(0).getType());
        assertEquals(Token.Type.VOICE, l.tokenList.get(8).getType());
    }
    
    @Test
    public void TestMixedCases() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("piece2.abc");
        assertEquals("Header: C:unknown K:C L:1/4 M:4/4 Q:200 T: Piece No.2 X:2 V:[] $$$ [^Fe]0.5 [Fe]0.5 z0.5 [Fe]0.5 z0.5 [Fc]0.5 [Fe] |" +
        		" [Gbg] z G z | c1.5 G0.5 z E | E0.5 a b _b0.5  a | (3Geg a' f0.5 g0.5 | z0.5 e c0.5 d0.5 b0.75 z0.75 |",
                l.toString());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestIllegalSingles() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_illegal_single.abc");
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
