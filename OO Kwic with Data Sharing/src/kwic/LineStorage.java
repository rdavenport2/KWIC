package kwic;

import java.util.ArrayList;

public class LineStorage implements IStorage{
    
    String text;
    ArrayList<LineIndex> indexes;

    public LineStorage() {
        indexes = new ArrayList<>();
    }
    
    @Override
    public LineIndex get(int i){return indexes.get(i);}
    
    @Override
    public void add(LineIndex e){indexes.add(e);}

    @Override//good
    public char getChar(int index) {
        //System.out.println("in LineStorage.getChar");
        return text.charAt(index);
    }

    @Override//good
    public int lengthOfLine(int beginIndex) {
        //System.out.println("in LineStorage.lengthOfLine");
        int index = beginIndex;
        int count = 1;
        
        while (index < text.length()  && !Character.toString(text.charAt(index)).equals("\n")){
            count ++;
            index ++;          
        }
        
        return count;
    }
   
    @Override//good
    public int wordCount(int beginIndex) {
        //System.out.println("in LineStorage.wordCount");
        int count = 1;
        int index = beginIndex;
        int lastIndex = lengthOfLine(beginIndex) + beginIndex - 1;
        while (index < lastIndex){
           if(text.charAt(index) == 32 || Character.toString(text.charAt(index)).equals("\n")) {
               count ++;
           }
           index ++;
        }
        return count;
    }
    
    @Override//good
    public int lineCount(){
        //System.out.println("in LineStorage.lineCount");
        int count = 1;       
        for(int index = 0; index < text.length(); index++ ){
            if(Character.toString(text.charAt(index)).equals("\n")){
                count ++;
            }
        }
        
        return count;
    }

    @Override//good
    public void setData(String data) {
        //System.out.println("in LineStorage.setData");
        this.text = data;
    }

    @Override
    public int length() {
        //System.out.println("in LineStorage.length");
        return text.length();
    }
    
    @Override
    public void displayIndexes(){
        for(LineIndex i: indexes){
            System.out.println(i.toString());
        }
  
    }
    
    @Override
    public int size(){return indexes.size();}
    
    @Override
    public int indexOf(LineIndex i){return indexes.indexOf(i);}
    
}//end class
