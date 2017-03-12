package kwic;

public class InputModule extends Module{
   
    public InputModule(IStorage vault) {
        super(vault);
    }
    
    public void setData(String text){
        System.out.println("\nin InputModule.setData");
        text = text.trim();
        vault.setData(text + "\n");
        System.out.println(text);
        compileIndexes(text);
    }
    
    private void compileIndexes(String text){
        //compiles the begining index of each line
        for(int i = 0; i < text.length(); i++){
            
            if(i == 0){
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(i);
                li.setLineLength(vault.lengthOfLine(i));
                li.setPlaceInSort(0);
                li.setWordOffset(0);
                vault.add(li);
            }
            if(Character.toString(text.charAt(i)).equals("\n")){
                LineIndex li = new LineIndex();
                li.setLineBeginningIndex(i + 1);
                li.setLineLength(vault.lengthOfLine(i + 1));
                li.setPlaceInSort(0);
                li.setWordOffset(0);
                vault.add(li);
            } 
        }
        vault.displayIndexes();
    }
}//end class
