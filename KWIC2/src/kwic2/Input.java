package kwic2;

import java.util.ArrayList;

public class Input {

    LineStorage storage;
    //ArrayList<String> list;
    String text;

    public Input() {
        storage = new LineStorage();
        //list = new ArrayList<>();
        text = "";
    }

    public void read(String input) {
        text = input;
        //System.out.println("in input.read " + text);
    }//end read
    
    public void store(){
        //System.out.println("in input.store");
        if(text.trim().isEmpty()){
            //System.out.println("input is empty");
        }else{        
            //look at each char in the string
                //if it is a letter, store
                //if it is a new line, go to new line #
                //if it is a new word go to new word #
                //advance to next pos #
                int lineNumber = 0;
                int wordNumber = 0;
                int pos = 0;
                char c;
            for(int i = 0; i < text.length(); i++){
                c = text.charAt(i);
                storage.setchar(lineNumber, wordNumber, pos, c);
                //System.out.println(lineNumber + " " + wordNumber + " " + pos + " " + c);
                if (Character.toString(c).equals("\n")){             
                    lineNumber ++;
                    wordNumber = 0;
                    pos = 0;
                }else if(c == 32){
                    wordNumber ++;
                    pos = 0;
                }else{
                    pos ++;
                }
            }   
        }//end if               
    }//end store
        
}//end class
