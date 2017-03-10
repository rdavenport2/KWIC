
import java.util.ArrayList;

public class Module {
    protected IStorage vault;
    protected ArrayList<LineIndex> previousModIndexes;
    protected ArrayList<LineIndex> newIndexes;
    
    public Module(IStorage vault, ArrayList<LineIndex> previousIndexes){
        this.vault = vault;
        this.previousModIndexes = previousIndexes;
        newIndexes = new ArrayList<>();
    }

    public ArrayList<LineIndex> getNewIndexes() {
        return newIndexes;
    }

    public void setNewIndexes(ArrayList<LineIndex> newIndexes) {
        this.newIndexes = newIndexes;
    }

    public IStorage getVault() {
        return vault;
    }

    public void setVault(IStorage vault) {
        this.vault = vault;
    }

    public ArrayList<LineIndex> getPreviousModIndexes() {
        return previousModIndexes;
    }

    public void setPreviousModIndexes(ArrayList<LineIndex> previousModIndexes) {
        this.previousModIndexes = previousModIndexes;
    }

    protected void displayIndexes(){
        for(LineIndex i: newIndexes){
            System.out.println(i.toString());
        }
    }
}//end class
