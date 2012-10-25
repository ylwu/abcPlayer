package player;

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
		try{
		    Feeder feeder = new Feeder(new Parser(new Lexer()));
		}
		catch(NumberFormatException e){
		    throw new RuntimeException("NumberFormatException")
		}
	}
}
