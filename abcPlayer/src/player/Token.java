package player;

public class Token {
    
    /**
     *  All the types of token that can be made
     *  LINE: |
     *  ACCIDENTAL: ^ or _ or =
     *  NOTE: a-gA-G
     *  OCTAVE: ' or ,
     *  LEFTBRA: [
     *  RIGHTBRA: ]
     *  LEFTPAR: (
     *  COLON: :
     *  Rest: z
     *  LENGTH: number that represent the length of the note, such as 2, 1/3.
     *  VOICE: V (followed by the name of the voice)
    */
    public static enum Type{
        LINE,ACCIDENTAL,NOTE,OCTAVE,LEFTBRA,RIGHTBRA,LEFTPAR,COLON,REST,LENGTH,VOICE
    }
    
    private final Type type;
    private final String str;
    
    public Token(String str, Type type){
        this.type = type;
        this.str = str;    
    }
    
    @Override
    public String toString(){
        return str;
    }
    
    public Type getType(){
        return type;
    }

}
