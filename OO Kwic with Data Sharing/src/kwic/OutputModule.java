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
       // super.previousModIndexes = kwic.AlphabetizerModule.getInstance().getNewIndexes();
    }
    
    public static OutputModule getInstance(){return instance;}

    void show() {
        System.out.println("\nin OutputModule.show");
        
        String output = "";
        
        //caluclate how many to output
        int maxIteration = 0;
        for(int j = 0; j < vault.size(); j++){
            if(!vault.get(j).isRemoved()){
                maxIteration ++;
            }
        }
        int place = 1;
        int iteration = 0;
        while(iteration < maxIteration){
            //if the line wraps, change the \n to a space and change space to \n at the end
            for(int i = 0; i < vault.size(); i++){
                //find the first line to output
                if(vault.get(i).getPlaceInSort() == place){
                    for(int j = 0; j < vault.get(i).getLineLength(); j++){
                        int lineStoragePosition = getPosition(vault.get(i), j);
                        String nextChar = Character.toString(vault.getChar(lineStoragePosition));
                        if(nextChar.equals("\n") && vault.get(i).getWordOffset() != 0){
                            //this is not the end of the line
                            nextChar = " ";
                        }
                        if(j ==  vault.get(i).getLineLength() - 1){
                            //this is the end of the line
                            nextChar = "\n";
                        }
                        output += nextChar;
                        System.out.print(nextChar);
                    }
                    place ++;
                }
            }
            iteration ++;
        }
        KWICControl control = KWICControl.getInstance();
        control.outputArea.setText(control.outputArea.getText() + output);
    }//end show
    
    private int getPosition(LineIndex l, int index){
            
            int current = (index + l.getWordOffset()) % l.getLineLength() + l.getLineBeginningIndex();         
            return current; 
        }
    
}//end class
