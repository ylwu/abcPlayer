package player;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lexer {
    public Header header;
    public ArrayList<Token> tokenList;
    private String state;
    private String lastHeader;
    private int index;
    private int currentStrLength;
    private String currentStr;
    
    public Lexer(){
        state = "header";
        tokenList = new ArrayList<Token>();
        header = new Header();
    }
        
    public void tokenize(String file) throws IOException{
        FileInputStream fstream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        String firstLine = br.readLine();
        if (firstLine.substring(0, 2).equals("X:")){
            header.X = firstLine.substring(2).trim();
        } else {
            throw new IllegalArgumentException(
                    "The first field in the header must be the index number ('X').");
        }
        
        String secondLine = br.readLine();
        if (secondLine.substring(0, 2).equals("T:")){
            header.T = secondLine.substring(2);
        } else {
            throw new IllegalArgumentException(
                    "The second field in the header must be the index number ('T').");
        }
        
        String strLine;
        while (state == "header"){
           strLine = br.readLine();
               String first = strLine.substring(0,1);
               if (first.equals("C")){
                   String second = strLine.substring(1,2);
                   if (second.equals(":")){
                       header.C = strLine.substring(2).trim();
                   } else {
                       state = "body";
                       tokenizeLine(strLine);
                   }                
               } else if (first.equals("K")){
                   header.K = strLine.substring(2).trim();
                   lastHeader = "K";
                   state = "body";
               } else if (first.equals("L")){
                   header.L = strLine.substring(2).trim();
                   lastHeader = "L";
               } else if (first.equals("M")){
                   header.M = strLine.substring(2).trim();
                   lastHeader = "M";
               } else if (first.equals("Q")){
                   header.Q = strLine.substring(2).trim();
                   lastHeader = "Q";
               } else if (first.equals(" ")){
                   
               } else if (first.equals("V")){
                   header.V.add(strLine.substring(2).trim());
               }
               else {
                   state = "body";
                   tokenizeLine(strLine);
               }               
           }  
        
        //state == Body
        if (!lastHeader.equals("K")){
            throw new IllegalArgumentException(
                    "The last field in the header must be the index number ('K').");
        }
        
        while ((strLine = br.readLine()) != null){
            if (strLine.substring(0,1).equals("V")){
                tokenList.add(new Token(strLine.substring(2),Token.Type.VOICE));
            } else {    
            tokenizeLine(strLine.trim());
            }
        }
    }
    
    public void tokenizeLine(String str){
        index = 0;
        currentStr = str;
        currentStrLength = str.length();
        while (hasNext()){
            getNextToken();
        }
    }
    
    private boolean hasNext(){
        return (index < currentStrLength );         
    }
    
    private void getNextToken(){
        String s = currentStr.substring(index, index+1);
        if (s.matches("[abcdefgABCDEFG]")){
            tokenList.add(new Token(s,Token.Type.NOTE));     
        } else if (s.matches("\\|")){
            tokenList.add(new Token(s,Token.Type.LINE));  
        } else if (s.matches("\\^|_|=")){
            tokenList.add(new Token(s,Token.Type.ACCIDENTAL)); 
        } else if (s.matches("[',]")){
            tokenList.add(new Token(s,Token.Type.OCTAVE));
        } else if (s.matches("\\[")){
            tokenList.add(new Token(s,Token.Type.LEFTBRA)); 
        } else if (s.matches("\\]")){
            tokenList.add(new Token(s,Token.Type.RIGHTBRA));
        } else if (s.matches("\\(")){
            tokenList.add(new Token(s,Token.Type.LEFTBRA));            
        } else if (s.matches("\\:")){
            tokenList.add(new Token(s,Token.Type.COLON));
        } else if (s.matches("z")){
            tokenList.add(new Token(s,Token.Type.REST));
        } else if (s.matches("[0-9]|\\/")){
            int x = 1;
            while (currentStr.substring(index,index + x).matches("[0-9]*\\/?[0-9]*")){
                x += 1;
            }
            tokenList.add(new Token(currentStr.substring(index,index + x -1),Token.Type.LENGTH));
            index += x - 2;
        } else if (s.matches("%")){
            index = currentStrLength -1;
        } else if (s.equals(" ")){
        } else {
            System.out.println(s);
            throw new IllegalArgumentException("unrecognized character");
            
        }
        
        index += 1;
    }
    
    private String tokenString(){
        String TokenString = "";
        for (Token t : tokenList){
            TokenString += t.toString();
            TokenString += " ";
        }
        return TokenString;
    }
    
    @Override
    public String toString(){
        return "Header: " + header.toString() + " $$$ " + tokenString();
    }

}
