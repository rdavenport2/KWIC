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
                System.out.print(vault.getChar(getPosition(previousModIndexes.get(i), j)));
            }
        }
    }//end show
    
    private int getPosition(LineIndex l, int index){
            
            int lineBegin = l.getLineBeginningIndex();
            int virtualLineBegin = lineBegin + l.getWordOffset();
            int current = (index % l.getLineLength()) + virtualLineBegin;
            
           /* if(virtualLineBegin != lineBegin){      
                //need to wrap
                //don't forget the \n is counted in the line as a space
                current = current % l.getLineLength();
            }*/
            return current; 
        }
    
}//end class
