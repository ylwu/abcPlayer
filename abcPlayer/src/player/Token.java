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
     *  DUPLET: (2
     *  TRIPLET: (3
     *  QUADRUPLET: (4
     *  COLON: :
     *  Rest: z
     *  LENGTH: number that represent the length of the note, such as 2, 1/3.
     *  VOICE: V: (followed by the name of the voice)
     *  AlTONE: [1
     *  ALTTWO: [2
     *  SPACE: " "
    */
    public static enum Type{
        LINE,ACCIDENTAL,NOTE,OCTAVE,LEFTBRA,RIGHTBRA,DUPLET,TRIPLET,QUADRUPLET,COLON,REST,
        LENGTH,VOICE,ALTONE,ALTTWO,SPACE;
    }
    
    private final Type type;
    private final String str;
    private final Double val;
    
    public Token(String str, Type type){
        this.type = type;
        this.str = str;
        this.val = null;
    }
    
    public Token(Double val, Type type){
        this.type = type;
        this.str = null;
        this.val = val;
    }
    
    @Override
    public String toString(){
        if (str!= null) return str;
        else return Double.toString(val);
    }
    
    public Type getType(){
        return type;
    }
    
    public String getValue(){
    	return str;
    }
}
