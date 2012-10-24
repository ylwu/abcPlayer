package player;

public interface Expression {
	
	public String getType();
	public String toString();
	
	public class Line implements Expression {
		
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
		public Line(Token token){
			this.thisToken = token;
		}
		
		public String getType(){
			return "Line";
		}		

		public String toString(){
			return this.value;
		}
	}

	public class Section implements Expression {
		
		public String getType(){
			return "Section";
		}
		
		public String toString(){
			return "This is a Section";
		}
	}
	
	public class Accidental implements Expression {

		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
	
	public class Colon implements Expression {
		
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
		public Colon(Token token){
			this.thisToken = token;
		}
		
		public String getType(){
			return "Colon";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Rest implements Expression {
		
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
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
		private final String value = this.thisToken.getValue();
		
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
	
	public class Voice implements Expression {
		
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
		public Voice(Token token){
			this.thisToken = token;
		}
		
		public String getType(){
			return "Voice";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class AltOne implements Expression {
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
		public AltOne(Token token){
			this.thisToken = token;
		}
		
		public String getType(){
			return "AltOne";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class AltTwo implements Expression {
		
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
		public AltTwo(Token token){
			this.thisToken = token;
		}
		
		public String getType(){
			return "AltTwo";
		}		

		public String toString(){
			return this.value;
		}
	}
	
	public class Space implements Expression {
		private Token thisToken;
		private final String value = this.thisToken.getValue();
		
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