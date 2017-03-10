
import java.util.ArrayList;
import java.util.Comparator;

public class AlphabetizerModule extends Module{

    public AlphabetizerModule(IStorage vault, ArrayList<LineIndex> previousIndexes) {
        super(vault, previousIndexes);
    }
    
    void sort() {
        System.out.println("\nAlphabetizerModule.sort");
        
        NewComparator newSort = new NewComparator();
        
        //compare each char in a line from previous module
        for (int j = 0; j < previousModIndexes.size(); j++) {
            /* find the min element in the unsorted a[j .. n-1] */
            int place = j + 1;
            /* assume the min is the first element */
            LineIndex min = previousModIndexes.get(j);
            /* test against elements after j to find the smallest */
            for (int i = j+1; i < previousModIndexes.size(); i++) {
                /* if this element is less, then it is the new minimum */
                LineIndex current = previousModIndexes.get(i);
                int result = newSort.compare(min, current);
                    System.out.println("result: " + result);
                if (current.getPlaceInSort() == 0 && newSort.compare(min, current) > 0  ) {                    
                    /* found new minimum; remember its index */
                    min = current;
                    System.out.println("min: " + min.getLineBeginningIndex());
                }
            }
            
            int a = previousModIndexes.indexOf(min);
            newIndexes.add(previousModIndexes.get(a));
            newIndexes.get(a).setPlaceInSort(place);
            
        }
        
        newIndexes = previousModIndexes;
        displayIndexes();
    }//end sort
    
    private class NewComparator implements Comparator<LineIndex>{
        
        int result;
        
        @Override
        public int compare(LineIndex c1, LineIndex c2) {
            //System.out.println("\nAlphabetizerModule.NewComparator");
            CharacterComparator cc = new CharacterComparator();
            int index1 = c1.getLineBeginningIndex() + c1.getWordOffset();
            int index2 = c2.getLineBeginningIndex() + c2.getWordOffset();
            //System.out.println("index 1: " + index1);
            //System.out.println("index 2: " + index2);
            int shortestLength;
            if(c1.getLineLength() < c2.getLineLength()){
                shortestLength = c1.getLineLength();
            }else{
                shortestLength = c2.getLineLength();
            }
            
            //this checks for the same letters at the begining of the line
            while(index1 < shortestLength && cc.compare(vault.getChar(index1), vault.getChar(index2)) == 0){
                //need to wrap to the begining of the line
                //System.out.println("Char are the same");
                index1 ++;
                index2 ++;
                index1 = wrap(index1, c1.getLineLength());
                index2 = wrap(index2, c2.getLineLength());
            }
            
            //these char are different
            // < 0 
            result = cc.compare(vault.getChar(index1), vault.getChar(index2));
            //System.out.println("result: " + result);
            
        return result;
     
        }//end compare
        
        int wrap(int index, int length){
            if(Character.toString(vault.getChar(index)).equals("\n")){
                return index + length -1;
            }else{
                return index;
            }
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

                result = Character.toString(c1).compareTo(Character.toString(c2));

            } //different case, same letter - reverse the compare result
            else {

                result = -1 * Character.toString(c1).compareTo(Character.toString(c2));
            }

            return result;
        }
    
    }

}//end class
