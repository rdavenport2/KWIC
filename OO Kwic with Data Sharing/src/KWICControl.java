
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class KWICControl {

    //declare variables
    private JTextArea outputArea;
    private JTextArea inputArea;
    private final JButton startButton;
    
    public KWICControl(){
    JFrame frame = new JFrame("KWIC Indexing System");
        // Add a window listner for close button
        frame.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e) {
                        System.exit(0);
                }
        });
        // This is an empty content area in the frame
        frame.getContentPane();
        frame.setSize(400,400); 

        JPanel mainFrame = new JPanel(new BorderLayout());

        JPanel centerLayout = new JPanel();
        centerLayout.setLayout(new BoxLayout(centerLayout, BoxLayout.Y_AXIS));

        inputArea = new JTextArea();
        inputArea.setText("we\nwant\nwith");
        inputArea.setBorder(BorderFactory.createTitledBorder("Input"));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        centerLayout.add(inputScroll);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        centerLayout.add(outputScroll);
        
        startButton = new JButton("Start");
        ActionListener startListener = new StartListener();
        startButton.addActionListener(startListener);

        mainFrame.add(centerLayout, BorderLayout.CENTER);
        mainFrame.add(startButton, BorderLayout.SOUTH);
        frame.add(mainFrame);   
        frame.setVisible(true);		
    }//end constructor

    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {            
            indexingSystem(inputArea.getText());
        }//end actionPerformed        
    }//end listener
    
    public static void main(String[] args) {
        KWICControl app = new KWICControl();
    }//end main
    
    private void indexingSystem(String text){
        
        //create data storage interface
        IStorage vault = new LineStorage();
        
        //create input module and input text
        InputModule input = new InputModule(vault, null);
        input.setData(text);
        
        //testLineStorage(vault);
        
        //create ciurcular shift module and begin shift
        CircularShiftModule circularShift = new CircularShiftModule(vault, input.getNewIndexes());
        circularShift.shift();
        
        //create noise word line removal module and begin line removal
        RemoveNoiseWordModule removeNoiseWord = new RemoveNoiseWordModule(vault, circularShift.getNewIndexes());
        removeNoiseWord.removeLine();
        
        //create alphabetizer module and begin sort
        AlphabetizerModule alphabetizer = new AlphabetizerModule(vault, removeNoiseWord.getNewIndexes());
        alphabetizer.sort();
        
        //create output module and ouput results
        //OutputModule output = new OutputModule(vault);
        //output.show();
        
    }//end indexingSystem
    
    public void testLineStorage(IStorage vault){
        //test line count
        int lineCount = vault.lineCount();
        System.out.println("line count: " + lineCount);
        
        //test length of line
        int line1Length = vault.lengthOfLine(0);
        System.out.println("line 1 length: " + line1Length);
        
        int line2Length = vault.lengthOfLine(line1Length);
        System.out.println("line 2 length: " + line2Length);
        
        //test word count
        int wordCountLine1 = vault.wordCount(0);
        int wordCountLine2 = vault.wordCount(line1Length);
        System.out.println("line 1 word count: " + wordCountLine1);
        System.out.println("line 2 word count: " + wordCountLine2);
        
        //test get char
        System.out.println("testing getchar: ");
        for (int i = 0; i < inputArea.getText().trim().length(); i++){
            System.out.print(vault.getChar(i));
        }

    }
    
}//end class
