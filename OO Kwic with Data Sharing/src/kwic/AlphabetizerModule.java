package kwic;
import java.util.Comparator;

public class AlphabetizerModule extends Module{
    
    public AlphabetizerModule(IStorage vault) {
        super(vault);
    }
    
    void sort() {
        System.out.println("\nAlphabetizerModule.sort");
        
        LineComparator lineSort = new LineComparator();
         
        int iteration = 0;
        int maxIteration = 0;
        int place = 1;
        for(int j = 0; j < vault.size(); j++){
            if(!vault.get(j).isRemoved()){
                maxIteration ++;
            }
        }
        while (iteration < maxIteration){
            int min = 0;
            
            //get the first index number that has not been placed yet and assume it is the min
            while (min < vault.size() && (vault.get(min).isRemoved() || vault.get(min).getPlaceInSort() != 0)){
                min ++;             
            }
            
            //compare the min with all the lines, if it is not the min, assign new value to min
            for(int i = 0; i < vault.size(); i++){
                if(min < vault.size() && vault.get(i).getPlaceInSort() == 0 && !vault.get(i).isRemoved()){
                    
                    int result = lineSort.compare(vault.get(min), vault.get(i));
                    if(result > 0){
                        min = i;
                    }
                }
            }
            
            //min should be the mininum; give it the next place in line
            vault.get(min).setPlaceInSort(place);
            place ++;
            iteration ++;
        }//end outer while
        
        //newIndexes = previousModIndexes;
        vault.displayIndexes();
    }//end sort
    
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
            
            //compare the fist two letters:
            int result = cc.compare(vault.getChar(getPosition(c1, index1)), vault.getChar(getPosition(c2, index2)));
            //System.out.println("result: " + result);
            
            //if the letters are the same move to the next letters
            //don't go past the shortest length
            while(result == 0 && index1 < shortestLength){
                index1 ++;
                index2 ++;
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
