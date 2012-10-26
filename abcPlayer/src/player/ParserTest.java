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
    
    @Test
    public void basicTest3() throws IOException{
        Lexer l = new Lexer();
        l.tokenize("piece1.abc");
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
        public void randomTest() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("random.abc");
            Parser p = new Parser(l);
            String expected = "Voice:1---Section( Single{e3.0} Single{G1.0} Single{f1.0} Single{e1.0}" +
            		" ) Section( Single{d3.0} Single{F1.0} Single{e1.0} Single{d1.0} ) Section" +
            		"( Single{c3.0} Single{E1.0} Single{d1.0} Single{c1.0} ) Section( Single{B2.0} " +
            		"Single{z1.0} Single{E1.0} Single{e1.0} Single{z1.0} ) Section( Single{z1.0} " +
            		"Single{e1.0} Single{e'1.0} Single{z1.0} Single{z1.0} Single{^d1.0} )  " +
            		"Voice:2---Section( Single{C,1.0} Single{E,1.0} Single{C1.0} Single{z1.0} " +
            		"Single{z2.0} ) Section( Single{G,1.0} Single{,1.0} Single{G,1.0} Single{B,1.0} " +
            		"Single{z1.0} Single{z2.0} ) Section( Single{A,1.0} Single{,1.0} Single{E,1.0}" +
            		" Single{A,1.0} Single{z1.0} Single{z2.0} ) Section( Single{E,1.0} Single{,1.0} " +
            		"Single{E,1.0} Single{E1.0} Single{z1.0} Single{z1.0} Single{E1.0} ) Section( " +
            		"Single{e1.0} Single{z1.0} Single{z1.0} Single{^d1.0} Single{e1.0} Single{z1.0} )  ";
            assertEquals(expected,p.toString());
            System.out.println(p.toString());
        }
        
        @Test
        public void spaceTest() throws IOException{
            Lexer l = new Lexer();
            l.tokenize("parser_space.abc");
            Parser p = new Parser(l);
            String expected = "Voice:---Section( Single{G3.0} Single{a1.0} Single{b1.0} Single{b1.0} )  ";
            assertEquals(expected,p.toString());
        }
        
    }