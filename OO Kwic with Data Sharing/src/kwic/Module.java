package kwic;
import java.util.ArrayList;

public class Module {
    protected IStorage vault;
    
    public Module(IStorage vault){
        this.vault = vault;
    }
    
}//end class
