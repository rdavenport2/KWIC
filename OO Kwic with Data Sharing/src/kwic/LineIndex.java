package kwic;
public class LineIndex {
    
    private int lineBeginningIndex;//
    private int wordOffset;//
    private int lineLength;//
    private int placeInSort;
    private boolean removed;//
    
    public LineIndex(){
    
    }

    public int getLineBeginningIndex() {
        return lineBeginningIndex;
    }

    public void setLineBeginningIndex(int lineBeginningIndex) {
        this.lineBeginningIndex = lineBeginningIndex;
    }

    public int getWordOffset() {
        return wordOffset;
    }

    public void setWordOffset(int wordOffset) {
        this.wordOffset = wordOffset;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getPlaceInSort() {
        return placeInSort;
    }

    public void setPlaceInSort(int placeInSort) {
        this.placeInSort = placeInSort;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "LineIndex{" + "lineBeginningIndex=" + lineBeginningIndex + 
                ", wordOffset=" + wordOffset + 
                ", lineLength=" + lineLength + 
                ", placeInSort=" + placeInSort + 
                ", removed=" + removed + '}';
    }
   
}//end class
