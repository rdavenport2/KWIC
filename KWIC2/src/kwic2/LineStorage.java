package kwic2;

import java.util.ArrayList;

public class LineStorage {
    ArrayList<ArrayList<String>> lineStorage;

    public LineStorage() {
        lineStorage = new ArrayList<>();
    }
    
    
    public void setchar(int line, int word, int pos, char letter) {
        
        if(word == 0 && pos == 0){
        //create a new line
            ArrayList<String> newLine = new ArrayList<>();
            lineStorage.add(newLine);
            //System.out.println("added a new line");
        }
        
        if(pos == 0 ){
            //create a new word
            String newWord = "";
            lineStorage.get(line).add(newWord);
            //System.out.println("added a new word");
        }
        
        String w = lineStorage.get(line).get(word);
        w += letter;
        //System.out.println("new word: " + w);
        lineStorage.get(line).remove(word);
        lineStorage.get(line).add(w);
       
    }
    
    public char getchar(int line, int word, int pos){
        return lineStorage.get(line).get(word).charAt(pos);    
    }
    
    //public int word(int line){}
    
    public void display(){
        for(int i = 0; i < lineStorage.size(); i++){
            for(int j = 0; j < lineStorage.get(i).size(); j++){
                System.out.print(lineStorage.get(i).get(j));
            }
        }
    }
    
}
