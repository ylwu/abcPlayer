package player;

import java.util.*;

import player.Token;

public class Parser {

	public Parser(Lexer lexer) {
		this.tokenList = lexer.getTokenList();
		this.lexer = lexer;
        this.header = lexer.getHeader();
		this.parsedList = voiceMaker();		
		for (Expression.Voice e: parsedList){
			e.setSections(repeatedSection(sectionMaker(e.getTokenInVoice())));
			ArrayList<Expression.Section> sections = e.getSections();
			for (Expression.Section sect: sections){
				System.out.println(sect.getTokenSection().toString());
				sectionToNotes(sect);
			}
		}
	}
	
	private ArrayList<Token> tokenList;
	private ArrayList<Expression.Voice> parsedList;
	private Lexer lexer;
	public Header header;
	
	private ArrayList<Expression.Voice> voiceMaker(){
		ArrayList<String> listOfVoice = new ArrayList<String>();
		ArrayList<ArrayList<Token>> tokenInVoice = new ArrayList<ArrayList<Token>>();
		String voiceOfTime = "";
		ArrayList<Token> listOfTime = new ArrayList<Token>();
		if (!tokenList.get(0).getType().equals(Token.Type.VOICE)){
		    Expression.Voice v = new Expression.Voice("");
		    v.setTokenInVoice(tokenList);
		    ArrayList<Expression.Voice> setVoice = new ArrayList<Expression.Voice>();
		    setVoice.add(v);
		    return setVoice;
		} else {
			for (Token token: tokenList){
				if (token.getType().equals(Token.Type.VOICE)){	    
					String voiceName = token.toString();
					if (!listOfVoice.equals("")){
						tokenInVoice.set(listOfVoice.indexOf(voiceOfTime), listOfTime);
						listOfTime = new ArrayList<Token>();
					} 
					listOfVoice.add(voiceName);
				} else {
					listOfTime.add(token);
				}
			}
		}
		tokenInVoice.add(listOfTime);
		ArrayList<Expression.Voice> setVoice = new ArrayList<Expression.Voice>();
		int length = listOfVoice.size();
		for (int i=0; i<length; i++){
			Expression.Voice v = new Expression.Voice(listOfVoice.get(i));
			v.setTokenInVoice(tokenInVoice.get(i));
		}
		return setVoice;
	}
	
	private ArrayList<Expression.Section> sectionMaker(ArrayList<Token> tokenList){
		int length = tokenList.size();
		ArrayList<Integer> lineIndex = new ArrayList<Integer>();
		lineIndex.add(0);
		for (int i=1; i<length; i++){
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
			for (int i=fromIndex; i<=toIndex; i++){
			    section.add(tokenList.get(i));
			}
			listOfExpression.add(new Expression.Section(section));
		}
		return listOfExpression;
	}
	
	private ArrayList<Expression.Section> repeatedSection(List<Expression.Section> list) {
	    boolean repeat = false;
	    boolean altOne = false;
	    ArrayList<Expression.Section> newList = new ArrayList<Expression.Section>();
	    ArrayList<Expression.Section> repeatedSection = new ArrayList<Expression.Section>();
	    ArrayList<Expression.Section> altOneSection = new ArrayList<Expression.Section>();
	    for (Expression.Section e: list){
	        ArrayList<Token> tokenSection =
	                e.getTokenSection();
	        int length = tokenSection.size();
	        int lengthNewList = newList.size();
	        if (altTwo(e)){
	            ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
                Expression.Section newE = new Expression.Section(newTokenSection); 
                newList.add(newE);
	        } else if (altOne) {
	            if (endRepeat(e)){
                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
                    Expression.Section newE = new Expression.Section(newTokenSection); 
                    newList.addAll(repeatedSection);
                    newList.addAll(altOneSection);
                    newList.add(newE);
                    repeatedSection = new ArrayList<Expression.Section>();
                    altOneSection = new ArrayList<Expression.Section>();
                    repeat = false;
                    altOne = false;
	            } else {
	                altOneSection.add(e);
	            }
	        } else {
    	        if (!repeat) {
    	            repeat = beginRepeat(e);
    	            if (repeat) {
    	                if (endRepeat(e)){
    	                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length-1);
                            Expression.Section newE = new Expression.Section(newTokenSection); 
    	                    newList.add(newE);
    	                    newList.add(newE);
    	                } else {
    	                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
    	                    Expression.Section newE = new Expression.Section(newTokenSection); 
    	                    newList.add(newE);
    	                    repeatedSection.add(newE);
    	                }
    	            } else {
    	                if (altOne(e)) {
    	                    if (endRepeat(e)){
    	                        ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length-1);
    	                        Expression.Section newE = new Expression.Section(newTokenSection); 
    	                        newList.add(newE);
    	                        newList.add(newList.get(lengthNewList-1));
    	                    } else {
    	                        altOne = true;
    	                        ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(1, length);
                                Expression.Section newE = new Expression.Section(newTokenSection);
                                altOneSection.add(newE);
                                repeatedSection.add(newList.get(lengthNewList-1));
    	                    }
    	                } else if (endRepeat(e)) {
    	                    ArrayList<Token> newTokenSection = (ArrayList<Token>) e.getTokenSection().subList(0, length-1);
    	                    Expression.Section newE = new Expression.Section(newTokenSection);
    	                    newList.add(newE);
    	                    newList.add(newList.get(lengthNewList-1));
    	                    newList.add(newE);
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
                            repeatedSection = new ArrayList<Expression.Section>();
                            repeat = false;
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
    	                repeatedSection = new ArrayList<Expression.Section>();
    	                repeat = false;
    	            } else {
    	                newList.add(e);
    	                repeatedSection.add(e);
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
	
	private void sectionToNotes (Expression.Section sect){
	    ArrayList<Token> tokenSection = sect.getTokenSection();
	    int count = 0;
    	boolean duplet = false;
    	boolean triplet = false;
    	boolean quadruplet = false;
    	int pletCount = 0;
	    ArrayList<Expression> listNote = new ArrayList<Expression>();
	    ArrayList<Token> noteToken = new ArrayList<Token>();
	    for (Token token: tokenSection){
	    	int i = typeHashCode(token.getType());
	    	if (token.getType().equals(Token.Type.DUPLET)){
	    		duplet = true;
	    	} else if (duplet){
	    		if (i < count){
					pletCount++;
					count = 0;
			       	if (pletCount >= 2){
			       		duplet = false;
			       		listNote.addAll(makeDuplet(noteToken));
			       		noteToken = new ArrayList<Token>();
			       	} else {
			       		noteToken.add(token);
			       	}
				} else {
			       	count = i;
			       	noteToken.add(token);
				}
	    	} else if (token.getType().equals(Token.Type.TRIPLET)){
	    		triplet = true;
	    	} else if (triplet) {
	    		if (i < count){
					pletCount++;
					count = 0;
			       	if (pletCount >= 3){
			       		triplet = false;
			       		listNote.addAll(makeTriplet(noteToken));
			       		noteToken = new ArrayList<Token>();
			       	} else {
			       		noteToken.add(token);
			       	}
				} else {
			       	count = i;
			       	noteToken.add(token);
				}
	    	} else if (token.getType().equals(Token.Type.QUADRUPLET)||quadruplet){
	    		quadruplet = true;
	    	} else if (quadruplet) {
	    		if (i < count){
					pletCount++;
					count = 0;
			       	if (pletCount >= 4){
			       		quadruplet = false;
			       		listNote.addAll(makeQuadruplet(noteToken));
			       		noteToken = new ArrayList<Token>();
			       	} else {
			       		noteToken.add(token);
			       	}
				} else {
			       	count = i;
			       	noteToken.add(token);
				}
	    	} else {
				if (i < count){
			       	listNote.add(makeNote(noteToken));
			       	noteToken = new ArrayList<Token>();
			       	noteToken.add(token);
			       	count = 0;
				} else {
			       	count = i;
			       	noteToken.add(token);
				}
	    	}
	    	if (duplet) {
	    		listNote.addAll(makeDuplet(noteToken));
	    	} else if (triplet) {
	    		listNote.addAll(makeTriplet(noteToken));
	    	} else if (quadruplet) {
	    		listNote.addAll(makeQuadruplet(noteToken));
	    	} else {
	    	listNote.add(makeNote(noteToken));
	    	}
	    }
	    sect.setNotes(listNote);
	}
	
	private int typeHashCode(Token.Type type){
		if (type.equals(Token.Type.ACCIDENTAL)){
			return 0;
		} else if (type.equals(Token.Type.NOTE)||
				type.equals(Token.Type.REST)||
				type.equals(Token.Type.LEFTBRA)||
				type.equals(Token.Type.RIGHTBRA)){
			return 1;
		} else if (type.equals(Token.Type.OCTAVE)){
			return 2;
		} else if (type.equals(Token.Type.LENGTH)){
			return 3;
		} else {
			return 20;
		}
	}
	
	private Expression makeNote(ArrayList<Token> noteToken){
		boolean chord = false;
		for (Token token: noteToken){
			if (token.getType().equals(Token.Type.LEFTBRA)){
				chord = true;
			}
		}
		if (chord) {
			ArrayList<Expression> notes = new ArrayList<Expression>();
			Expression.Chord c = new Expression.Chord();
			c.setNote(notes);
			return c;
		} else {
			Expression.SingleNote note = new Expression.SingleNote();
			for (Token token: noteToken){
				if (token.getType().equals(Token.Type.ACCIDENTAL)){
					note.setAccidental(setTokenExpression(token));
				} else if (token.getType().equals(Token.Type.NOTE)||token.getType().equals(Token.Type.REST)){
					note.setNote(setTokenExpression(token));
				} else if (token.getType().equals(Token.Type.OCTAVE)){
					note.setOctave(setTokenExpression(token));
				} else if (token.getType().equals(Token.Type.LENGTH)){
					note.setLength(setTokenExpression(token));
				} else {}
			}
			return note;
		}
	}
	
	private ArrayList<Expression> makeDuplet(ArrayList<Token> noteToken){
		int count = 0;
		ArrayList<Token> singleNoteToken = new ArrayList<Token>();
		ArrayList<Expression> listSingleNote = new ArrayList<Expression>();
		for (Token token: noteToken){
			int i = typeHashCode(token.getType());
			if (i < count){
				int length = singleNoteToken.size();
				Token lastToken = singleNoteToken.get(length-1);
				if (lastToken.getType().equals(Token.Type.LENGTH)){
					double newLength = lastToken.getValue()*0.5;
					Token newLengthToken = new Token(newLength, Token.Type.LENGTH);
					singleNoteToken.set(length-1, newLengthToken);
				} else {
					Token newLengthToken = new Token(0.5, Token.Type.LENGTH);
					singleNoteToken.add(newLengthToken);
				}
				Expression singleNote = makeNote(singleNoteToken);
				listSingleNote.add(singleNote);
				singleNoteToken = new ArrayList<Token>();
				singleNoteToken.add(token);
		       	count = 0;
			} else {
		       	count = i;
		       	singleNoteToken.add(token);
			}
		}
		Expression singleNote = makeNote(singleNoteToken);
		listSingleNote.add(singleNote);
		return listSingleNote;
	}
	
	private ArrayList<Expression> makeTriplet(ArrayList<Token> noteToken){
		int count = 0;
		ArrayList<Token> singleNoteToken = new ArrayList<Token>();
		ArrayList<Expression> listSingleNote = new ArrayList<Expression>();
		for (Token token: noteToken){
			int i = typeHashCode(token.getType());
			if (i < count){
				int length = singleNoteToken.size();
				Token lastToken = singleNoteToken.get(length-1);
				if (lastToken.getType().equals(Token.Type.LENGTH)){
					double newLength = lastToken.getValue()*(2.0/3);
					Token newLengthToken = new Token(newLength, Token.Type.LENGTH);
					singleNoteToken.set(length-1, newLengthToken);
				} else {
					Token newLengthToken = new Token(2.0/3, Token.Type.LENGTH);
					singleNoteToken.add(newLengthToken);
				}
				Expression singleNote = makeNote(singleNoteToken);
				listSingleNote.add(singleNote);
				singleNoteToken = new ArrayList<Token>();
				singleNoteToken.add(token);
		       	count = 0;
			} else {
		       	count = i;
		       	singleNoteToken.add(token);
			}
		}
		Expression singleNote = makeNote(singleNoteToken);
		listSingleNote.add(singleNote);
		return listSingleNote;
	}
	
	private ArrayList<Expression> makeQuadruplet(ArrayList<Token> noteToken){
		int count = 0;
		ArrayList<Token> singleNoteToken = new ArrayList<Token>();
		ArrayList<Expression> listSingleNote = new ArrayList<Expression>();
		for (Token token: noteToken){
			int i = typeHashCode(token.getType());
			if (i < count){
				int length = singleNoteToken.size();
				Token lastToken = singleNoteToken.get(length-1);
				if (lastToken.getType().equals(Token.Type.LENGTH)){
					double newLength = lastToken.getValue()*(0.75);
					Token newLengthToken = new Token(newLength, Token.Type.LENGTH);
					singleNoteToken.set(length-1, newLengthToken);
				} else {
					Token newLengthToken = new Token(0.75, Token.Type.LENGTH);
					singleNoteToken.add(newLengthToken);
				}
				Expression singleNote = makeNote(singleNoteToken);
				listSingleNote.add(singleNote);
				singleNoteToken = new ArrayList<Token>();
				singleNoteToken.add(token);
		       	count = 0;
			} else {
		       	count = i;
		       	singleNoteToken.add(token);
			}
		}
		Expression singleNote = makeNote(singleNoteToken);
		listSingleNote.add(singleNote);
		return listSingleNote;
	}
	
	private Expression setTokenExpression (Token token){
		if (token.getType().equals(Token.Type.ACCIDENTAL)){
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
		} else if (token.getType().equals(Token.Type.REST)){
			return new Expression.Rest(token);
		} else if (token.getType().equals(Token.Type.LENGTH)){
			return new Expression.Length(token);
		} else {
			return new Expression.Space(token);
		} 
	}
	
	public List<Expression.Voice> getParsedList(){
		return this.parsedList;
	}
	
	public String toString(){
	    StringBuffer s = new StringBuffer();
	    for (Expression e: parsedList){
	        s.append(e.toString());
	        s.append(" ");
	    }
	    return s.toString();
	}
}
