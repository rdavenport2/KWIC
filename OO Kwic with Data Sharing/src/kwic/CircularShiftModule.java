package kwic;
import java.util.ArrayList;

public class CircularShiftModule extends Module{
    
    public CircularShiftModule(IStorage vault) {
        super(vault);
        }
   
    //this shift creates shifted lines from index list
    public void shift(){
        System.out.println("\nin CircularShiftModule.shift");
        //for each begining index, create a list of shifts
        int size = vault.size();
        for(int i = 0; i < size; i++){
            //LineIndex li = vault.get(i);
            System.out.println(vault.get(i).toString());
            int lastIndex = vault.get(i).getLineLength()+vault.get(i).getLineBeginningIndex() - 2;
            System.out.println("lastIndex: " + lastIndex);
            for(int index = vault.get(i).getLineBeginningIndex(); index <= lastIndex; index++){
                System.out.println("index: " + index);
                //first word
                if(vault.getChar(index) == 32){
                    System.out.println("in index == 32");
                    LineIndex next = new LineIndex();
                    next.setLineBeginningIndex(vault.get(i).getLineBeginningIndex());
                    next.setRemoved(false);
                    next.setWordOffset(index + 1 - vault.get(i).getLineBeginningIndex());
                    next.setLineLength(vault.get(i).getLineLength());
                    next.setPlaceInSort(0);
                    vault.add(next);
                }     
            }
            
        }
        vault.displayIndexes();
    }

}//end class
