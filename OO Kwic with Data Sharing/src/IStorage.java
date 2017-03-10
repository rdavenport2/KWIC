public interface IStorage {
    
    public void setData(String data);
    
    public char getChar(int index);
    
    public int lengthOfLine (int lineNumber);
    
    public int wordCount(int lineNumber);
    
    public int lineCount();
    
    public int length();
    
}//end interface
