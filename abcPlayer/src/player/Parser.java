package player;

import java.util.*;
import player.Token;

public class Parser {

	public Parser(Lexer lexer) {
		this.tokenList = lexer.getTokenList();
	}
	
	private List<Token> tokenList;
	private List<Expression> parsedList;
	
	private List<Expression> createExpressionList (List<Token> listOfToken) {
		List<Expression> listOfExpression = new ArrayList<Expression>(); 
		for (Token token: listOfToken) {
			Expression expr = setExpression(token);
			listOfExpression.add(expr);
		}
		return listOfExpression;
	}
	
	private Expression setExpression (Token token){
		if (token.getType().equals(Token.Type.)){
			
		}
	}
}
