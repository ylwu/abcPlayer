package player;


import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class ParserTest {
    
    //@Test
    public void basicTest() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_chords.abc");
        System.out.println(l.toString());
        Parser p = new Parser(l);
        System.out.println(p.toString());
    }
    
    //@Test
    public void basicTest2() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("lexer_legal_voices.abc");
        System.out.println(l.toString());
        Parser p = new Parser(l);
        System.out.println(p.toString());
    }
    
    //@Test
    public void basicTest3() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("piece2.abc");
        System.out.println(l.toString());
        Parser p = new Parser(l);
        System.out.println(p.toString());
    }      
        //@Test
        public void basicTestChords() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("lexer_legal_chords.abc");
            Parser p = new Parser(l);
            String expected = "Voice:---Section( Chord[Single{A1.0} Single{B1.0} Single{C1.0} ] Chord[Single{A1.0} Single{B1.0} ] )  ";
            assertEquals(expected,p.toString());
        }
        
        //@Test
        public void basicTestSingles() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("lexer_legal_single.abc");
            Parser p = new Parser(l);
            String expected = "Voice:---Section( Single{A1.0} Single{B1.0} Single{C1.0} Single{D1.0}" +
                    " Single{E1.0} Single{F1.0} ) Section( Single{G1.0} Single{a1.0} Single{b1.0} Single{c1.0}" +
                    " Single{d1.0} Single{e1.0} Single{f1.0} Single{g1.0} )  ";
            assertEquals(expected,p.toString());
        }
        
        //@Test
        public void basicTestVoices() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("lexer_legal_voices.abc");
            Parser p = new Parser(l);
            String expected = "Voice:1---Section( Single{A1.0} Single{B1.0} Single{C1.0} Single{D1.0} )  " +
                    "Voice:2---Section( Single{A1.0} Single{B1.0} Single{C1.0} Single{D1.0} )  Voice:A---" +
                    "Section( Single{A1.0} Single{B1.0} Single{C1.0} Single{D1.0} )  ";
            assertEquals(expected,p.toString());
        }
        
        
        @Test
        public void BasicTestSpaces() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("parser_space.abc");
            Parser p = new Parser(l);
            String expected = "Voice:---Section( Single{G3.0} Single{a1.0} Single{b1.0} Single{b1.0} )  ";
            assertEquals(expected,p.toString());
        }
        
        @Test
        public void multipleOctaveTest() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("doubleOctave");
            System.out.println(l.toString());
            Parser p = new Parser(l);
            System.out.println(p.toString());
        }
        
    }