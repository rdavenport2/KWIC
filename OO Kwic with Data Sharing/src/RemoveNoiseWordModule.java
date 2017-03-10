
import java.util.ArrayList;
import java.util.Arrays;

public class RemoveNoiseWordModule extends Module{
    
    ArrayList<String> noiseWords;

    public RemoveNoiseWordModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
        noiseWords = new ArrayList<>(Arrays.asList(new String[]{
            "a", "an", "the", "and", "or", "nor", "yet", "for", "but",
            "am", "is", "are", "was", "were", "be", "being", "been",
            "of", "to", "in", "out", "by", "as", "at", "off", "on"
        }));
    }
 
    void removeLine() {
        System.out.println("\nin RemoveNoiseWordModule.removeLine");
        /*
        Examine each begining word from shiftIndexes to eliminate
        */
        for(int i = 0; i < previousModIndexes.size(); i++){
            String firstWord = "";
            LineIndex l = previousModIndexes.get(i);
            int index = l.getLineBeginningIndex() + l.getWordOffset();
            while(index < vault.length() && vault.getChar(index) != 32  && 
                    !Character.toString(vault.getChar(index)).equals("\n")){
                firstWord += vault.getChar(index);
                index ++;
            }
            //System.out.println("FirstWord: " + firstWord);
            for(String noiseWord: noiseWords){
            //if the 1st word is not a noise word, put the line in the pipe
                if(firstWord.equalsIgnoreCase(noiseWord)){
                   // System.out.println(firstWord + " is a noise word.");
                    l.setRemoved(true);
                    break;
                } 
            }
            if(!l.isRemoved()){
                newIndexes.add(l);
            }
        }
        displayIndexes();
    }    
}//end class
