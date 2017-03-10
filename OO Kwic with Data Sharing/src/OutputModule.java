import java.util.ArrayList;

public class OutputModule extends Module{

    public OutputModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
    }

    void show() {
        System.out.println("\nin OutputModule.show");
        
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
                System.out.print(nextChar);
            }
        }
    }//end show
    
    private int getPosition(LineIndex l, int index){
            
            int current = (index + l.getWordOffset()) % l.getLineLength();         
            return current; 
        }
    
}//end class
