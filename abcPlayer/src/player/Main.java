package player;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API and displays
	 * header information to the standard output stream.
	 * 
	 * <p>Your code <b>should not</b> exit the application abnormally using
	 * System.exit()</p>
	 * 
	 * @param file the name of input abc file
	 */
	public static void play(String file) {
	    try {
	        Lexer lexer = new Lexer();
	        lexer.tokenize(file);
            Feeder feeder = new Feeder(new Parser(lexer));
            feeder.addAll();
            feeder.play();
	    } catch (NumberFormatException e){
	        System.out.println("Error playing file!");
        } catch (MidiUnavailableException e) {
            System.out.println("Error playing file! Midi unavailable");
        } catch (InvalidMidiDataException e) {
            System.out.println("Error playing file! Invalid Midi data");
        } catch (IOException e){
            System.out.println("Error loading file!");
        }
	}
}
