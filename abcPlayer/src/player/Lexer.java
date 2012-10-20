package player;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lexer {
    public Header header;
    public ArrayList tokenList;
    
    public Lexer(){
        
    }
    
    private boolean hasNext(){
                
    }
    
    public void tokenize(String file) throws IOException{
        FileInputStream fstream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null){
           
        }

    }
    
    public void tokenizeLine(String string){
        
    }
    
    

}
