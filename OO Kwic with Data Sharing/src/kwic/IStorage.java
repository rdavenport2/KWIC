package kwic;
public interface IStorage {
    
    public LineIndex get(int i);
    
    public void add(LineIndex e);
    
    public void setData(String data);
    
    public char getChar(int index);
    
    public int lengthOfLine (int lineNumber);
    
    public int wordCount(int lineNumber);
    
    public int lineCount();
    
    public int length();
    
    public void displayIndexes();
    
    public int size();
    
    public int indexOf(LineIndex i);
    
}//end interface
