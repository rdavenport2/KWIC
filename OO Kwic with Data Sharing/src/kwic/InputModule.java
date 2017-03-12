package kwic;
import java.util.ArrayList;

public class InputModule extends Module{
    
    private static InputModule instance;

    /*public InputModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
        instance = InputModule.this;
    }*/
    
    public InputModule(IStorage vault) {
        super(vault);
        instance = InputModule.this;
    }
    
    public static InputModule getInstance(){return instance;}
    
    public void setData(String text){
        System.out.println("\nin InputModule.setData");
        text = text.trim();
        vault.setData(text + "\n");
        System.out.println(text);
        compileIndexes(text);
    }
    
    private void compileIndexes(String text){
        //compiles the begining index of each line
        for(int i = 0; i < text.length(); i++){
            
            if(i == 0){
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(i);
                li.setLineLength(vault.lengthOfLine(i));
                li.setPlaceInSort(0);
                li.setWordOffset(0);
                //newIndexes.add(li);
                vault.add(li);
            }
            if(Character.toString(text.charAt(i)).equals("\n")){
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(i + 1);
                li.setLineLength(vault.lengthOfLine(i + 1));
                li.setPlaceInSort(0);
                li.setWordOffset(0);
                //newIndexes.add(li);
                vault.add(li);
            } 
        }
        //displayIndexes();
        vault.displayIndexes();
    }
}//end class
