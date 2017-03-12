package kwic;
import java.util.Comparator;

public class AlphabetizerModule extends Module{
    
    private static AlphabetizerModule instance;

    /*public AlphabetizerModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
    }*/
    
    public AlphabetizerModule(IStorage vault) {
        super(vault);
        instance = AlphabetizerModule.this;
        super.previousModIndexes = kwic.RemoveNoiseWordModule.getInstance().getNewIndexes();
    }
    
    public static AlphabetizerModule getInstance(){return instance;}
    
    void sort() {
        System.out.println("\nAlphabetizerModule.sort");
        
        LineComparator lineSort = new LineComparator();
        
        //compare each char in a line from previous module
        for (int j = 0; j < previousModIndexes.size()-1; j++) {
            /* find the min element in the unsorted a[j .. n-1] */
            //int place = j + 1;
            /* assume the min is the first element */
            LineIndex min = previousModIndexes.get(j);
            /* test against elements after j to find the smallest */
            for (int i = j+1; i < previousModIndexes.size(); i++) {
                /* if this element is less, then it is the new minimum */
                LineIndex current = previousModIndexes.get(i);
                int result = lineSort.compare(min, current);
                //System.out.println("result: " + result);
                if (result > 0  ) {                    
                    /* found new minimum; remember its index */
                    min = current;
                    //System.out.println("min: " + min.getLineBeginningIndex());
                }
            }
            if(min != previousModIndexes.get(j)){
                //swap
                int m = previousModIndexes.indexOf(min);
                swap(j, m);
            }    
        }
        
        newIndexes = previousModIndexes;
        displayIndexes();
    }//end sort
    
    private void swap(int j, int m){
        
        LineIndex tempj = previousModIndexes.get(j);
        LineIndex tempm = previousModIndexes.get(m);
        previousModIndexes.remove(j);
        previousModIndexes.remove(m-1);
        previousModIndexes.add(j, tempm);
        previousModIndexes.add(m, tempj);   
    }
    
    private class LineComparator implements Comparator<LineIndex>{
        
        int result;
        
        @Override
        public int compare(LineIndex c1, LineIndex c2) {
            //System.out.println("\nAlphabetizerModule.NewComparator");
            //find the shortest line
            int shortestLength;
            if(c1.getLineLength() < c2.getLineLength()){
                shortestLength = c1.getLineLength();
            }else{
                shortestLength = c2.getLineLength();
            }
            
            CharacterComparator cc = new CharacterComparator();
            
            //index - position in virtual line
            int index1 = 0;
            int index2 = 0;
            
            //get the correct index number in line storage to compare
            //int compare_pos1 = getPosition(c1, index1);
            //int compare_pos2 = getPosition(c2, index2);
            
            //compare the fist two letters:
            int result = cc.compare(vault.getChar(getPosition(c1, index1)), vault.getChar(getPosition(c2, index2)));
            //System.out.println("result: " + result);
            
            //if the letters are the same move to the next letters
            //don't go past the shortest length
            while(result == 0 && index1 < shortestLength){
                index1 ++;
                index2 ++;
                //compare_pos1 = getPosition(c1, index1);
                //compare_pos2 = getPosition(c2, index2);
                result = cc.compare(vault.getChar(getPosition(c1, index1)), vault.getChar(getPosition(c2, index2)));
            }
            
        return result;
     
        }//end compare
        
        private int getPosition(LineIndex l, int index){
            
            int current = (index + l.getWordOffset()) % l.getLineLength() + l.getLineBeginningIndex();
            return current; 
        }

    }//end Comparator
    
    private class CharacterComparator implements Comparator<Character>{
        int result;
        @Override
        public int compare(Character c1, Character c2) {
            //System.out.println("\nAlphabetizerModule.CharacterComparator");
            //both upper case - compare as usual
            if (c1 <= 90 && c2 <= 90) {

                result = Character.toString(c1).compareTo(Character.toString(c2));

            } //both lower case - compare as usual
            else if (c1 >= 97 && c2 >= 97) {

                result = Character.toString(c1).compareTo(Character.toString(c2));

            } /*different case, different letter - compare as usual ignoring case*/
            else if (!Character.toString(c1).toUpperCase().equals(Character.toString(c2).toUpperCase())) {

                result = Character.toString(c1).compareToIgnoreCase(Character.toString(c2));

            } //different case, same letter - reverse the compare result
            else {

                result = -1 * Character.toString(c1).compareTo(Character.toString(c2));
            }

            return result;
        }
    
    }

}//end class
