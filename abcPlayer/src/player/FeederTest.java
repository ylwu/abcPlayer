package player;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

public class FeederTest {

    @Test
    public void piece1Test() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("piece1.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
    @Test
    public void piece2Test() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("piece2.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
    @Test
    public void inventionTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("invention.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
    @Test(expected = RuntimeException.class)
    public void furEliseTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("piece1.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
    
    @Test(expected = IOException.class)
    public void noSuchFileTest() throws MidiUnavailableException, InvalidMidiDataException, IOException{
        Lexer lexer = new Lexer();
        lexer.tokenize("idontexist.abc");
        Feeder feeder = new Feeder(new Parser(lexer));
        feeder.play();
    }
}
