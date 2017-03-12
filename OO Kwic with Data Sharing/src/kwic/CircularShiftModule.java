package kwic;
import java.util.ArrayList;

public class CircularShiftModule extends Module{
    
    private static CircularShiftModule instance;

    /*public CircularShiftModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
        instance = CircularShiftModule.this;
        previousModIndexes = kwic.InputModule.getInstance().getNewIndexes();
    }*/
    
    public CircularShiftModule(IStorage vault) {
        super(vault);
        instance = CircularShiftModule.this;
        super.previousModIndexes = kwic.InputModule.getInstance().getNewIndexes();
    }
    
    public static CircularShiftModule getInstance(){return instance;}

    //this shift creates shifted lines from index list
    public void shift(){
        System.out.println("\nin CircularShiftModule.shift");
        //for each begining index, create a list of shifts
        for(int i = 0; i < previousModIndexes.size(); i++){
            LineIndex li = previousModIndexes.get(i);
            //System.out.println(li.toString());
            int lastIndex = li.getLineLength()+li.getLineBeginningIndex() - 2;
            //System.out.println("lastIndex: " + lastIndex);
            for(int index = li.getLineBeginningIndex(); index <= lastIndex; index++){
                //System.out.println("index: " + index);
                //first word
                if(index == li.getLineBeginningIndex()){
                    //System.out.println("in index == begin index");
                    LineIndex next = new LineIndex();
                    next.setLineBeginningIndex(li.getLineBeginningIndex());
                    next.setRemoved(false);
                    next.setWordOffset(0);
                    next.setLineLength(li.getLineLength());
                    newIndexes.add(next);
                }else if(vault.getChar(index) == 32){
                    //System.out.println("in index == 32");
                    LineIndex next = new LineIndex();
                    next.setLineBeginningIndex(li.getLineBeginningIndex());
                    next.setRemoved(false);
                    next.setWordOffset(index + 1 - li.getLineBeginningIndex());
                    next.setLineLength(li.getLineLength());
                    newIndexes.add(next);
                }     
            }
            
        }
        displayIndexes();
    }

    //this shift creates the shifted lines directly from vault
    //need to adjust to create shifted lines from index list
    public void shiftFromVault(){
        System.out.println("in CircularShiftModule.shift");
        /*
        create a new virtual line at the beginning of each word
        */
        
        int lineStartingIndex = 0;
        for(int index = 0; index < super.getVault().length(); index++){            
            //begining of input
            if(index == 0){
                //System.out.println("index == 0");
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(lineStartingIndex);
                li.setWordOffset(lineStartingIndex + index);
                li.setRemoved(false);
                super.getNewIndexes().add(li);
               
            }else if(super.getVault().getChar(index) == 32){
                //System.out.println("char == 32");
                //space means a new word
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(lineStartingIndex);
                li.setWordOffset(index + 1 - lineStartingIndex);
                li.setRemoved(false);
                super.getNewIndexes().add(li);
                
            }else if(Character.toString(super.getVault().getChar(index)).equals("\n")){
                //System.out.println("index == newLine");
                //new line character means next line
                lineStartingIndex = index + 1;
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(lineStartingIndex);
                li.setWordOffset(0);
                li.setRemoved(false);
                super.getNewIndexes().add(li);                
            }
        }
        
        displayIndexes();
    }
   

}//end class
