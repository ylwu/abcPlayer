package player;

import player.Expression.*;

/**
 * The visitor interface for the player.
 * @param <R>
 */
public interface Visitor<R> {
    public void onVoice(Voice voice);
    public void onNote(Note note);
    public void onChord(Chord chord);
}

