package player;

import sound.SequencePlayer;

/**
 * Class in charge of creating a SequencePlayer using the data in parser.lexer.header
 * and putting all parsed expressions into the player.
 */
public class Feeder {
	Parser parser;
	SequencePlayer player;
	int pitch;
	int defLen;
	
	/**
	 * Constructs a Feeder object from a Parser.
	 * @param parser
	 */
	public Feeder(Parser parser){
		this.parser = parser;
		headerToFields();
	}
	
	/**
	 * Adds all the expressions into the player.
	 * Takes the Expression object from this.parser, traverses down each voice and calls
	 * feedExpression() on each leaf.
	 */
	public void addAll(){
		
	}
	
	/**
	 * Takes the field header from the lexer and creates a SequencePlayer by using
	 * parameters specified by the head.
	 */
	public void headerToFields(){
	    Header header = this.parser.lexer.header;
		if (header.Q != null){
		    this.player = new SequencePlayer(Integer.parseInt(header.T), 12);
		}else{
		    this.player = new SequencePlayer(100, 12);
		}
		if (header.L != null){
		    this.defLen = 
		}
		
	}
	
	/**
	 * Takes an Expression object, analyzes its parameters, and input them into the sequence
	 * player. Utilizes visitor pattern.
	 * @param exp Object implementing Expression.
	 */
	public void feedExpression(Expression exp){
		
	}
	

}
