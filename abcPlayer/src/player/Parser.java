package player;

import java.util.*;
import player.Token;

public class Parser {

	public Parser(Lexer lexer) {
		this.tokenList = lexer.getTokenList();
		this.parsedList = createExpressionList(this.tokenList);
	}
	
	private List<Token> tokenList;
	private List<Expression> parsedList;
	
	private List<Expression> createExpressionList (List<Token> listOfToken) {
		List<Expression> listOfExpression = new ArrayList<Expression>(); 
		for (Token token: listOfToken) {
			Expression expr = setTokenExpression(token);
			listOfExpression.add(expr);
		}
		return listOfExpression;
	}
	
	private Expression setTokenExpression (Token token){
		if (token.getType().equals(Token.Type.LINE)){
			return new Expression.Line(token);
		} else if (token.getType().equals(Token.Type.ACCIDENTAL)){
			return new Expression.Accidental(token);
		} else if (token.getType().equals(Token.Type.NOTE)){
			return new Expression.Note(token);
		} else if (token.getType().equals(Token.Type.OCTAVE)){
			return new Expression.Octave(token);
		} else if (token.getType().equals(Token.Type.LEFTBRA)){
			return new Expression.LeftBra(token);
		} else if (token.getType().equals(Token.Type.RIGHTBRA)){
			return new Expression.RightBra(token);
		} else if (token.getType().equals(Token.Type.DUPLET)){
			return new Expression.Duplet(token);
		} else if (token.getType().equals(Token.Type.TRIPLET)){
			return new Expression.Triplet(token);
		} else if (token.getType().equals(Token.Type.QUADRUPLET)){
			return new Expression.Quadruplet(token);
		} else if (token.getType().equals(Token.Type.COLON)){
			return new Expression.Colon(token);
		} else if (token.getType().equals(Token.Type.REST)){
			return new Expression.Rest(token);
		} else if (token.getType().equals(Token.Type.LENGTH)){
			return new Expression.Length(token);
		} else if (token.getType().equals(Token.Type.VOICE)){
			return new Expression.Voice(token);
		} else if (token.getType().equals(Token.Type.ALTONE)){
			return new Expression.AltOne(token);
		} else if (token.getType().equals(Token.Type.ALTTWO)){
			return new Expression.AltTwo(token);
		} else {
			return new Expression.Space(token);
		} 
	}
	
	public List<Expression> getParsedList(){
		return this.parsedList;
	}
}
