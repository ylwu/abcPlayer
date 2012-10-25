package player;

import java.awt.print.Paper;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.easymock.EasyMock;
import org.junit.Test;

public class FeederTest {

    //@Test
    public void piece1Test() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("piece1.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.addAll();
        feeder.play();
        feeder.toString();
    }
    
    @Test
    public void piece2Test() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("piece2.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.addAll();
        feeder.play();
    }
    
    //@Test
    public void inventionTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("invention.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
   // @Test(expected = RuntimeException.class)
    public void furEliseTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("piece1.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
   // @Test(expected = IOException.class)
    public void noSuchFileTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("idontexist.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
    public void fromScratchTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Parser parser = EasyMock.createMock(Parser.class);
        ArrayList<Expression.Voice> vl = new ArrayList<Expression.Voice>();
        Expression.Voice v1 = new Expression.Voice("1");
        ArrayList<Expression.Section> sl = new ArrayList<Expression.Section>();
        ArrayList<Token> tl = new ArrayList<Token>();
        Expression.Section s1 = new Expression.Section(tl);
        ArrayList<Expression> notes = new ArrayList<Expression>();
        Token t1 = new Token("A", Token.Type.NOTE);
        Expression.Note n1 = new Expression.Note(t1);
        Expression.SingleNote sn1 = new Expression.SingleNote();
        sn1.setNote(n1);
        Token t2 = new Token("B", Token.Type.NOTE);
        Expression.Note n2 = new Expression.Note(t2);
        Expression.SingleNote sn2 = new Expression.SingleNote();
        sn1.setNote(n2);
        notes.add(sn1);
        notes.add(sn2);
        s1.setNotes(notes);
        sl.add(s1);
        v1.setSections(sl);
        vl.add(v1);
        
    }
}
