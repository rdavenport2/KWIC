package kwic;

public class OutputModule extends Module{
    
    public OutputModule(IStorage vault) {
        super(vault);
    }

    void show() {
        System.out.println("\nin OutputModule.show");
        
        String output = "";
        
        //caluclate how many to output
        int maxIteration = 0;
        for(int j = 0; j < vault.size(); j++){
            if(vault.get(j).getPlaceInSort() > maxIteration){
                maxIteration = vault.get(j).getPlaceInSort();
            }
        }
        int place = 1;
        int iteration = 0;
        while(iteration < maxIteration){
            //if the line wraps, change the \n to a space and change space to \n at the end
            for(int i = 0; i < vault.size(); i++){
                //find the first line to output
                if(vault.get(i).getPlaceInSort() == place  && !vault.get(i).isRemoved()){
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
