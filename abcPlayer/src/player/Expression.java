package player;

import java.util.*;

public interface Expression {
	
	public String getType();
	public String toString();
	
	public class Voice implements Expression {
		
		private String voiceName;
		private ArrayList<Token> tokenInVoice;
		private ArrayList<Expression.Section> sections;
		
		public Voice(String string){
			this.voiceName = string;
		}
		
		public String getType(){
			return "Voice";
		}
		
		@Override
		public String toString(){
		    StringBuffer s = new StringBuffer();
		    for (Expression.Section sec : sections){
		        s.append(sec.toString());
		    }
			return "Voice:"  + voiceName +"---"+ s;
			
		}
		
		public void setTokenInVoice(ArrayList<Token> tokenInVoice){
			this.tokenInVoice = tokenInVoice;
		}
		
		public void setSections(ArrayList<Expression.Section> sections){
			this.sections = sections;
		}
		
		public String getVoiceName(){
			return this.voiceName;
		}
		
		public ArrayList<Token> getTokenInVoice (){
			return this.tokenInVoice;
		}
		
		public ArrayList<Expression.Section> getSections(){
			return this.sections;
		}
	}
	
	public class MajorSection implements Expression {
		private ArrayList<Token> tokenSection;
		private ArrayList<Expression> sections;
		
        public MajorSection(ArrayList<Token> l){
            this.tokenSection = l;
        }
		
		public String getType(){
            return "MajorSection";
        }
        
        public String toString(){
            StringBuffer s = new StringBuffer();
            for (Expression e : sections){
                s.append(e.toString());
            }
            return "MajorSection( " + s + ") ";
        }
		
        public ArrayList<Token> getTokenSection(){
            return tokenSection;
        }
        
        public void setNotes(ArrayList<Expression> listSection){
        	this.sections = sections;
        }
        
        public ArrayList<Expression> getSections(){
            return this.sections;
        }
	}
	
    public class Section implements Expression {
        
        private ArrayList<Token> tokenSection;
        private ArrayList<Expression> notes;
        
        public Section(ArrayList<Token> l){
            this.tokenSection = l;
        }
        
        public String getType(){
            return "Section";
        }
        
        public String toString(){
            StringBuffer s = new StringBuffer();
            for (Expression e : notes){
                s.append(e.toString());
            }
            return "Section( " + s + ") ";
        }
        
        public ArrayList<Token> getTokenSection(){
            return tokenSection;
        }
        
        public void setNotes(ArrayList<Expression> listNotes){
        	this.notes = listNotes;
        }
        
        public ArrayList<Expression> getNotes(){
            return this.notes;
        }
    }
    
    public class SingleNote implements Expression {
    	
    	private Expression accidental;
    	private Expression note;
    	private Expression octave;
    	private Expression length;
    	
    	public SingleNote(){
    	    Token t = new Token(1.0,Token.Type.LENGTH);
    	    length = new Length(t);
    	}
    	
    	public String getType(){
    		return "SingleNote";
    	}
    	
    	public String toString(){
    	    String accStr = "";
    	    String noteStr = "";
    	    String octStr = "";
    	    String lenStr = "";
    	    
    	    if (accidental!=null) accStr = accidental.toString();
    	    if (note!= null) noteStr = note.toString();
    	    if (octave!= null) octStr = octave.toString();
    	    if (length!= null) lenStr = length.toString();
    		return "Single{" + accStr + noteStr + octStr + lenStr +"} ";
    	}
    	
    	public void setAccidental(Expression accidental){
    		this.accidental = accidental;
    	}
    	
    	public void setNote(Expression note){
    		this.note = note;
    	}
    	
    	public void setOctave(Expression octave){
    		this.octave = octave;
    	}
    	
    	public void setLength(Expression length){
    		this.length = length;
    	}
    	
    	public int getAccidental(){
    	    if (this.accidental == null){
    	        return 0;
    	    }else{
        	    if (this.accidental.toString().equals("^")){
        	        return 1;
        	    }else if (this.accidental.toString().equals("_")){
        	        return -1;
        	    }else if (this.accidental.toString().equals("=")){
        	        return 2;
        	    }else throw new RuntimeException("Illegal accidental");
    	    }
    	}
    	
    	public String getNote(){
    		return this.note.toString();
    	}
    	
    	public int getOctave(){
    	    if (this.octave == null){
                return 0;
            }else{
                if (this.octave.toString().equals("'")){
                    return 1;
                }else if (this.accidental.toString().equals(".")){
                    return -1;
                }else throw new RuntimeException("Illegal accidental");
            }
    	}
    	
    	public float getLength(){
    		return Float.parseFloat(this.length.toString());
    	}
    }
    
    public class Chord implements Expression {
    	
    	private ArrayList<Expression> notes;
 	
    	public Chord(){
    	}
    	
    	public String getType(){
    		return "Chord";
    	}
    	
    	public String toString(){
    		String notes = "";
    		for (Expression note: this.notes){
    			notes += note.toString();
    		}
    		return "Chord" +"["+notes+"] ";
    	}
    	
    	
    	public void setNote(ArrayList<Expression> notes){
    		this.notes = notes;
    	}
    	
    	public ArrayList<Expression> getNote(){
    		return this.notes;
    	}
    	
    }

	public class Accidental implements Expression {

		private Token thisToken;
		private final String value;
		
		public Accidental(Token token){
			thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Accidental";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Note implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public Note(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Note";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Octave implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public Octave(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Octave";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class LeftBra implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public LeftBra(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "LeftBra";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class RightBra implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public RightBra(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "RightBra";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Duplet implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public Duplet(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Duplet";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Triplet implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public Triplet(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Triplet";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Quadruplet implements Expression {

		private Token thisToken;
		private final String value;
		
		public Quadruplet(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Quadruplet";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Rest implements Expression {
		
		private Token thisToken;
		private final String value;
		
		public Rest(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Rest";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Length implements Expression {
		
		private Token thisToken;
		private String value;
		
		public Length(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Length";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Space implements Expression {
		private Token thisToken;
		private final String value;
		
		public Space(Token token){
			this.thisToken = token;
			value = token.toString();
		}
		
		public String getType(){
			return "Space";
		}		

		public String toString(){
			return this.value;
		}
	}
}
