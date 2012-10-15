package sound;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;



public class SequencePlayerTest {
    
    @Test
    public void piece1test() throws MidiUnavailableException, InvalidMidiDataException{
       
        SequencePlayer player = new SequencePlayer(140, 12);

        player.addNote(new Pitch('C').toMidiNote(), 0, 12);
        player.addNote(new Pitch('C').toMidiNote(), 12, 12);
        player.addNote(new Pitch('C').toMidiNote(), 24, 9);
        player.addNote(new Pitch('D').toMidiNote(), 33, 3);
        player.addNote(new Pitch('E').toMidiNote(), 36, 12);
        player.addNote(new Pitch('E').toMidiNote(), 48, 9);
        player.addNote(new Pitch('D').toMidiNote(), 57, 3);
        player.addNote(new Pitch('E').toMidiNote(), 60, 9);
        player.addNote(new Pitch('F').toMidiNote(), 69, 3);
        player.addNote(new Pitch('G').toMidiNote(), 72, 24);
        player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
        player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
        player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
        player.addNote(new Pitch('G').toMidiNote(), 108, 4);
        player.addNote(new Pitch('G').toMidiNote(), 112, 4);
        player.addNote(new Pitch('G').toMidiNote(), 116, 4);
        player.addNote(new Pitch('E').toMidiNote(), 120, 4);
        player.addNote(new Pitch('E').toMidiNote(), 124, 4);
        player.addNote(new Pitch('E').toMidiNote(), 128, 4);
        player.addNote(new Pitch('C').toMidiNote(), 132, 4);
        player.addNote(new Pitch('C').toMidiNote(), 136, 4);
        player.addNote(new Pitch('C').toMidiNote(), 140, 4);
        player.addNote(new Pitch('G').toMidiNote(), 144, 9);
        player.addNote(new Pitch('F').toMidiNote(), 153, 3);
        player.addNote(new Pitch('E').toMidiNote(), 156, 9);
        player.addNote(new Pitch('D').toMidiNote(), 165, 3);
        player.addNote(new Pitch('C').toMidiNote(), 168, 24);
        
        player.play();
        
        System.out.println(player);
 
    }
    
}
