package player;

import java.util.*;

import player.Expression.Section;
import player.Token;

public class Parser {

	public Parser(Lexer lexer) {
		this.tokenList = lexer.getTokenList();
		ArrayList<Expression.Section> intermediateList= sectionMaker();
		this.parsedList = repeatedSection(intermediateList);
	}
	
	private List<Token> tokenList;
	private List<Expression.Section> parsedList;
	
	private ArrayList<Section> sectionMaker(){
		int length = this.tokenList.size();
		ArrayList<Integer> lineIndex = new ArrayList<Integer>();
		for (int i=0; i<length; i++){
			if (tokenList.get(i).getType().equals(Token.Type.LINE)){
				lineIndex.add(i);
			}
		}
		ArrayList<Expression.Section> listOfExpression = new ArrayList<Expression.Section>();
		int numberOfLine = lineIndex.size();
		for (int j=1; j<numberOfLine; j++){
			int fromIndex = lineIndex.get(j-1) + 1;
			int toIndex = lineIndex.get(j);
			ArrayList<Token> section = new ArrayList<Token>();
			section = (ArrayList<Token>) this.tokenList.subList(fromIndex, toIndex);
			listOfExpression.add(new Expression.Section(section));
		}
		return listOfExpression;
	}
	
	private List<Expression.Section> repeatedSection(List<Expression.Section> list) {
	    boolean repeat = false;
	    boolean altOne = false;
	    ArrayList<Expression.Section> newList = new ArrayList<Expression.Section>();
	    ArrayList<Expression.Section> repeatedSection = new ArrayList<Expression.Section>();
	    ArrayList<Expression.Section> altOneSection = new ArrayList<Expression.Section>();
	    for (Expression.Section e: list){
	        ArrayList<Token> tokenSection =
	                e.getTokenSection();
	        int length = tokenSection.size();
	        if (altTwo(e)){
	            ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
                Expression.Section newE = new Expression.Section(newTokenSection); 
                newList.add(newE);
	        } else if (altOne) {
	            if (endRepeat(e)){
                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
                    Expression.Section newE = new Expression.Section(newTokenSection); 
                    newList.addAll(altOneSection);
                    newList.add(newE);
                    altOneSection = null;
	            } else {
	                altOneSection.add(e);
	            }
	        } else {
    	        if (!repeat) {
    	            repeat = beginRepeat(e);
    	            if (repeat) {
    	                if (endRepeat(e)){
    	                        newList.add(e);
    	                        newList.add(e);
    	                } else {
    	                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
    	                    Expression.Section newE = new Expression.Section(newTokenSection); 
    	                    newList.add(newE);
    	                    repeatedSection.add(newE);
    	                }
    	            } else {
    	                if (altOne(e)) {
    	                    if (endRepeat(e)){
    	                        ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
    	                        Expression.Section newE = new Expression.Section(newTokenSection); 
    	                        int lengthNewList = newList.size();
    	                        newList.add(newE);
    	                        newList.add(newList.get(lengthNewList-1));
    	                    } else {
    	                        altOne = true;
    	                        ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
                                Expression.Section newE = new Expression.Section(newTokenSection);
                                altOneSection.add(newE);
    	                    }
    	                } else if (endRepeat(e)) {
    	                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
    	                    Expression.Section newE = new Expression.Section(newTokenSection);
    	                    newList.add(newE);
    	                    int lengthNewList = newList.size();
    	                    newList.add(newList.get(lengthNewList-2));
    	                    newList.add(newList.get(lengthNewList-1));
    	                } else {
    	                    newList.add(e);
    	                }
    	            }
    	        } else {
    	            if (altOne(e)) {
                        if (endRepeat(e)){
                            ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
                            Expression.Section newE = new Expression.Section(newTokenSection); 
                            newList.add(newE);
                            newList.addAll(repeatedSection);
                            repeatedSection = null;
                        } else {
                            altOne = true;
                            ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
                            Expression.Section newE = new Expression.Section(newTokenSection);
                            altOneSection.add(newE);
                        }
    	            } else if (endRepeat(e)){
    	                ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
                        Expression.Section newE = new Expression.Section(newTokenSection); 
    	                newList.add(newE);
    	                newList.addAll(repeatedSection);
    	                newList.add(newE);
    	                repeatedSection = null;
    	            }
    	        }
    	    }
	    }
	    return newList;
	}
	
	private boolean beginRepeat(Expression.Section e){
	    if (e.getTokenSection().get(0).getType().equals(Token.Type.COLON)) return true;
	    return false;
	}
	
	private boolean endRepeat(Expression.Section e){
	    int indexLastToken = e.getTokenSection().size() - 1;
	    if (e.getTokenSection().get(indexLastToken).getType().equals(Token.Type.COLON)) return true;
	    return false;
	}
	
	private boolean altOne(Expression.Section e){
	    if (e.getTokenSection().get(0).getType().equals(Token.Type.ALTONE)) return true;
	    return false;
	}
	
	private boolean altTwo(Expression.Section e){
	    if (e.getTokenSection().get(0).getType().equals(Token.Type.ALTTWO)) return true;
	    return false;
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
	
	public List<Expression.Section> getParsedList(){
		return this.parsedList;
	}
}
