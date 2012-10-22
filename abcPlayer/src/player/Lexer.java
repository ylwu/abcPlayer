package player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lexer {
    public Header header;
    public ArrayList<Token> tokenList;
    private String state;
    private int index;
    private int currentStrLength;
    private String currentStr;
    
    public Lexer(){
        state = "header";
        tokenList = new ArrayList<Token>();
        header = new Header();
    }
        
    public void tokenize(String file) throws IOException{
        //FileInputStream fstream = new FileInputStream(file);
        //DataInputStream in = new DataInputStream(fstream);
        //BufferedReader br = new BufferedReader(new InputStreamReader(in));
        /*
        Use the lines above if you want to load the file from an absolute path in file system,
        eg."/Users/weidadianli/Desktop/6.005/projects/Project_1/abcPlayer/sample_abc/piece1.abc"
        */
        String default_path = "sample_abc/";
        BufferedReader br = new BufferedReader(new FileReader(default_path + file));
        /*
        Use the lines above if you want to load the file from java package.
        The default package to store input file is "/smaple_abc"
        */
        
        String firstLine = br.readLine().trim();
        if (firstLine.substring(0, 2).equals("X:")){
            header.X = firstLine.substring(2).trim();
        } else {
            throw new IllegalArgumentException(
                    "The first field in the header must be the index number ('X').");
        }
        
        String secondLine = br.readLine().trim();
        if (secondLine.substring(0, 2).equals("T:")){
            header.T = secondLine.substring(2);
        } else {
            throw new IllegalArgumentException(
                    "The second field in the header must be the index number ('T').");
        }
        
        String strLine;
        while (state == "header"){
           strLine = br.readLine();
           if (strLine.equals("")){              
           } else {
               strLine = strLine.trim();
               String pfx = strLine.substring(0,2);
               if (pfx.equals("C:")){
                   header.C = strLine.substring(2).trim();            
               } else if (pfx.equals("K:")){
                   header.K = strLine.substring(2).trim();
                   state = "body";
               } else if (pfx.equals("L:")){
                   header.L = strLine.substring(2).trim();
               } else if (pfx.equals("M:")){
                   header.M = strLine.substring(2).trim();
               } else if (pfx.equals("Q:")){
                   header.Q = strLine.substring(2).trim();
               } else if (pfx.equals("V:")){
                   header.V.add(strLine.substring(2).trim());
               }
               else {
                   throw new IllegalArgumentException(
                           "The last field in the header must be the index number ('K').");
               }               
           }  
        }
        
        while ((strLine = br.readLine()) != null){
            if (strLine.equals("")){
            } else if (strLine.substring(0,1).equals("V")){
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
