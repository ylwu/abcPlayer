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
		
		public String toString(){
			return voiceName;
		}
		
		public void setTokenInVoice(ArrayList<Token> tokenInVoice){
			this.tokenInVoice = tokenInVoice;
		}
		
		public void setSections(ArrayList<Expression.Section> sections){
			this.sections = sections;
		}
		
		public ArrayList<Token> getTokenInVoice (){
			return this.tokenInVoice;
		}
		
		public ArrayList<Expression.Section> getSections(){
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
            return "This is a Section";
        }
        
        public ArrayList<Token> getTokenSection(){
            return tokenSection;
        }
        
        public void setNotes(ArrayList<Expression> listNotes){
        	this.notes = listNotes;
        }
    }
    
    public class SingleNote implements Expression {
    	
    	private Expression accidental;
    	private Expression note;
    	private Expression octave;
    	private Expression length;
    	
    	public SingleNote(){
    	}
    	
    	public String getType(){
    		return "SingleNote";
    	}
    	
    	public String toString(){
    		return accidental.toString()+note.toString()+octave.toString()+length.toString();
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
    	
    	public Expression getAccidental(){
    		return this.accidental;
    	}
    	
    	public Expression getNote(){
    		return this.note;
    	}
    	
    	public Expression getOctave(){
    		return this.octave;
    	}
    	
    	public Expression getLength(){
    		return this.length;
    	}
    }
    
    public class Chord implements Expression {
    	
    	private Expression accidental;
    	private ArrayList<Expression> notes;
    	private Expression octave;
    	private Expression length;
    	
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
    		return accidental.toString()+"["+notes+"]"+octave.toString()+length.toString();
    	}
    	
    	public void setAccidental(Expression accidental){
    		this.accidental = accidental;
    	}
    	
    	public void setNote(ArrayList<Expression> notes){
    		this.notes = notes;
    	}
    	
    	public void setOctave(Expression octave){
    		this.octave = octave;
    	}
    	
    	public void setLength(Expression length){
    		this.length = length;
    	}
    	
    	public Expression getAccidental(){
    		return this.accidental;
    	}
    	
    	public ArrayList<Expression> getNote(){
    		return this.notes;
    	}
    	
    	public Expression getOctave(){
    		return this.octave;
    	}
    	
    	public Expression getLength(){
    		return this.length;
    	}
    }

	public class Accidental implements Expression {

		private Token thisToken;
		private final String value = this.thisToken.toString();
		
		public Accidental(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Note(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Octave(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public LeftBra(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public RightBra(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Duplet(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Triplet(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Quadruplet(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Rest(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Length(Token token){
			this.thisToken = token;
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
		private final String value = this.thisToken.toString();
		
		public Space(Token token){
			this.thisToken = token;
		}
		
		public String getType(){
			return "Space";
		}		

		public String toString(){
			return this.value;
		}
	}
}