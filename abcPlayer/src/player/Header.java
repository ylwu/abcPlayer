package player;

import java.util.ArrayList;

public class Header {
    public String C = "";
    public String K = "";
    public String L = "";
    public String M = "";
    public String Q = "";
    public String T = "";
    public String X = "";
    public ArrayList<String> V = new ArrayList<String>(); 
    
    
    public Header(){
        C = "unknown";
        L = "1/8";
        M = "4/4";
        Q = "100";
    }
    
    public String toString(){
        return "C:" + C + " K:" + K + " L:" + L + " M:" + M + " Q:" + Q + " T:" + T + " X:" + X + " V:" + V.toString(); 
    }
}
