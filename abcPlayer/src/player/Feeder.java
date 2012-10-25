package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import player.Expression.*;

import sound.Pitch;
import sound.SequencePlayer;

/**
 * Class in charge of creating a SequencePlayer using the data in parser.lexer.header
 * and putting all parsed expressions into the player.
 */
public class Feeder {
	private Parser parser;
	private SequencePlayer player;
	private LegalKey key;
	private double defLen;
	private int[] curTick;
	private int curVoice = 0;
	private static enum LegalKey{A, Ab, B, Bb, C, D, Db, E, Eb, F, Fs, G, Gb};
	
	
	/**
	 * Constructs a Feeder object from a Parser.
	 * @param parser
	 * @throws InvalidMidiDataException 
	 * @throws MidiUnavailableException 
	 * @throws NumberFormatException 
	 */
	public Feeder(Parser parser) throws NumberFormatException, MidiUnavailableException, InvalidMidiDataException{
		this.parser = parser;
        headerToFields();
	}
	
	/**
	 * Plays the data loaded in the player.
	 * @throws MidiUnavailableException
	 */
	public void play() throws MidiUnavailableException{
	    this.player.play();
	}
	
	/**
	 * Adds the current expression or all the expressions it contains into the player.
	 * Takes the Expression object from this.parser and recursively calls itself on each node.
	 */
	public void addAll(){
	    int vCount = -1;
	    this.curTick = new int[this.parser.getParsedList().size()];
		for (Voice v: this.parser.getParsedList()){
		    vCount++;
		    curVoice = vCount;
		    for (Section s: v.getSections()){
		        for (Expression e: s.getNotes()){
		            if (e.getType().equals("SingleNote")){
		                feedNote((SingleNote)e);
		                curTick[curVoice] += (int)Math.round((((SingleNote)e).getLength())/this.defLen*12);
		            }else if(e.getType().equals("Chord")){
		                feedChord((Chord)e);
		                curTick[curVoice] += (int)Math.round((((SingleNote)(((Chord)e).getNote().get(0))).getLength())/this.defLen*12);
		            }
		        }
		    }
		}
	}
	
	/**
	 * Takes the field header from the lexer and creates a SequencePlayer by using
	 * parameters specified by the head.
	 * @throws InvalidMidiDataException 
	 * @throws MidiUnavailableException 
	 * @throws NumberFormatException 
	 */
	private void headerToFields() throws NumberFormatException, MidiUnavailableException, InvalidMidiDataException{
	    Header header = this.parser.lexer.header;
        if (header.L != null){
            this.defLen = lengthToNumber(header.L);
        }else throw new RuntimeException("Illegal header input");
		if (header.Q != null){
		    this.player = new SequencePlayer(Integer.parseInt(header.T)*(int)Math.round(this.defLen/0.25), 12);
		}else throw new RuntimeException("Illegal header input");
		if (header.K != null){
		    int hasLK = hasLegalKey(header.K);
		    if (hasLK == 1){
		        if (header.K.equals("F#")){
		            this.key = LegalKey.Fs;
		        }else{ 
		            this.key = LegalKey.valueOf(header.K.substring(0,1));
		        }
		    }else if (hasLK == 2){
		        this.key = LegalKey.valueOf(header.K.substring(0,2)); 
		    }
		    else throw new RuntimeException("Illegal key");
		}
		else throw new RuntimeException("Illegal header input");
		if (header.V.isEmpty()){
		    this.curTick = new int[1];
		}else{
		    this.curTick = new int[header.V.size()];
		}
	}
	
	/**
	 * Takes an Note object, analyzes its parameters, and input them into the sequence
	 * player. 
	 * @param exp Note object.
	 */
	private void feedNote(SingleNote exp){
	    double length = exp.getLength();
	    int transpose = exp.getAccidental();
	    String note;
	    int octave=0;
	    
	    note = exp.getNote().toString();
	    if (exp.getOctave() == 1){
	        octave = 12;
	    }else if (exp.getOctave() == -1){
	        octave = -12;
	    }
	    if (transpose==2){
	        transpose=0;
	    }else{
    	    switch (this.key){
            case A:
                if ((exp.getNote() == "F")||(exp.getNote() == "C")||(exp.getNote() == "G")){
                    transpose += 1;
                }
                break;
            case Ab:
                if ((exp.getNote() == "B")||(exp.getNote() == "E")||(exp.getNote() == "A")||(exp.getNote() == "D")){
                    transpose -= 1;
                }
                break;
            case B:
                if ((exp.getNote() == "F")||(exp.getNote() == "C")||(exp.getNote() == "G")||(exp.getNote() == "D")||(exp.getNote() == "A")){
                    transpose += 1;
                }
                break;
            case Bb:
                if ((exp.getNote() == "B")||(exp.getNote() == "E")){
                    transpose -= 1;
                }
                break;
            case C:
                break;
            case D:
                if ((exp.getNote() == "F")||(exp.getNote() == "C")){
                    transpose += 1;
                }
                break;
            case Db:
                if ((exp.getNote() == "B")||(exp.getNote() == "E")||(exp.getNote() == "A")||(exp.getNote() == "D")||(exp.getNote() == "G")){
                    transpose -= 1;
                }
                break;
            case E:
                if ((exp.getNote() == "F")||(exp.getNote() == "C")||(exp.getNote() == "G")||(exp.getNote() == "D")){
                    transpose += 1;
                }
                break;
            case Eb:
                if ((exp.getNote() == "B")||(exp.getNote() == "E")||(exp.getNote() == "A")){
                    transpose -= 1;
                }
                break;
            case F:
                if (exp.getNote() == "B"){
                    transpose -= 1;
                }
                break;
            case Fs:
                if ((exp.getNote() == "F")||(exp.getNote() == "C")||(exp.getNote() == "G")||(exp.getNote() == "D")||(exp.getNote() == "A")||(exp.getNote() == "E")){
                    transpose += 1;
                }
                break;
            case G:
                if (exp.getNote() == "F"){
                    transpose += 1;
                }
                break;
            case Gb:
                if ((exp.getNote() == "B")||(exp.getNote() == "E")||(exp.getNote() == "A")||(exp.getNote() == "D")||(exp.getNote() == "G")||(exp.getNote() == "C")){
                    transpose -= 1;
                }
                break;
    	    }
	    }
		Pitch pitch = new Pitch(note.charAt(0)).transpose(transpose).transpose(octave);
		this.player.addNote(pitch.toMidiNote(),
		        curTick[curVoice], 
		        (int)Math.round(length/this.defLen*12));
	}
	
	/**
	 * Takes an Chord object, analyzes its parameters, and input them into the sequence
     * player. 
	 * @param exp The Chord object
	 */
	private void feedChord(Chord exp){
	    for (Expression s: exp.getNote()){
	        feedNote((SingleNote)s);
	    }
	}
	
	
	/**
	 * Takes a String representing a key signature of the music piece and tests if it could be in 
	 * the legal keys defined in enum LegalKeys. 
	 * @param input The input key signature
	 * @return Number of characters that match the name of a legal key
	 */
	private int hasLegalKey(String input) {
	    for (LegalKey k : LegalKey.values()) {
	        if (k.name().equals(input.substring(0,1))) {
	            if (k.name().equals(input.substring(0,2))){
	                return 2;
	            }return 1;
	        }
	    }
	    return 0;
	}
	
	/**
	 * Converts a string representing a fraction to the value in double
	 * @param str The string being parsed
	 * @return The number value represented by the string
	 */
	private double lengthToNumber(String str){
        double N = 1.0;
        double D = 8.0;
        int l = str.length();
        int index = str.indexOf("/");

        if (index == -1) {
            return Double.parseDouble((str));
        } else if (l == 1) {
            return 1.0/2; 
        } else if (index == 0) {
            D = Double.parseDouble(str.substring(1));
        } else if (index == l-1){
            N = Double.parseDouble(str.substring(0, l-1));
        } else {
            N = Double.parseDouble(str.substring(0,index));
            D = Double.parseDouble(str.substring(index+1,l));
        }
        return N / D;
    }
	
	private class MusicVisitor implements Visitor{
	    public MusicVisitor(){
	        
	    }
	    public void onVoice(Voice voice){

	    }
	    public void onNote(Note note){
	        
	    }
	    public void onChord(Chord chord){
	        
	    }
	}
	

}
