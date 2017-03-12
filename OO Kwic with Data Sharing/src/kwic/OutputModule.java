package kwic;
import java.util.ArrayList;
import kwic.KWICControl;

public class OutputModule extends Module{
    
    private static OutputModule instance;

    /*public OutputModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
    }*/
    
    public OutputModule(IStorage vault) {
        super(vault);
        instance = OutputModule.this;
        super.previousModIndexes = kwic.AlphabetizerModule.getInstance().getNewIndexes();
    }
    
    public static OutputModule getInstance(){return instance;}

    void show() {
        System.out.println("\nin OutputModule.show");
        
        String output = "";
        
        //output to screen for now
        //if the line wraps, change the \n to a space and change space to \n at the end
        for(int i = 0; i < previousModIndexes.size(); i++){
            for(int j = 0; j < previousModIndexes.get(i).getLineLength(); j++){
                int lineStoragePosition = getPosition(previousModIndexes.get(i), j);
                String nextChar = Character.toString(vault.getChar(lineStoragePosition));
                if(nextChar.equals("\n") && previousModIndexes.get(i).getWordOffset() != 0){
                    //this is not the end of the line
                    nextChar = " ";
                }
                if(j ==  previousModIndexes.get(i).getLineLength() - 1){
                    //this is the end of the line
                    nextChar = "\n";
                }
                output += nextChar;
                System.out.print(nextChar);
            }
        }
        KWICControl control = KWICControl.getInstance();
        control.outputArea.setText(control.outputArea.getText() + output);
    }//end show
    
    private int getPosition(LineIndex l, int index){
            
            int current = (index + l.getWordOffset()) % l.getLineLength() + l.getLineBeginningIndex();         
            return current; 
        }
    
}//end class
