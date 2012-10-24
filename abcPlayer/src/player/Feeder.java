package player;

import sound.SequencePlayer;

/**
 * Class in charge of creating a SequencePlayer using the data in parser.lexer.header
 * and putting all parsed expressions into the player.
 */
public class Feeder {
	Parser parser;
	SequencePlayer player;
	
	/**
	 * Constructs a Feeder object from a Parser.
	 * @param parser
	 */
	public Feeder(Parser parser){
		this.parser = parser;
		
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
	public SequencePlayer headerToPlayer(){
		
	}
	
	/**
	 * Takes an Expression object, analyzes its parameters, and input them into the sequence
	 * player. Utilizes visitor pattern.
	 * @param exp Object implementing Expression.
	 */
	public void feedExpression(Expression exp){
		
	}
	

}
