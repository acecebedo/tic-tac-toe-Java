import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


/**
 * Graphical user interface class for tic tac toe
 *
 * @ac980 | Ace Cebedo
 * @March 2022
 */
public class GUI
{
    // instance variables 
    private JFrame frame;
    private JMenuBar menubar;
    private JLabel topText;
    private JLabel topText2;
    private JButton[] gridButtons;
    private JButton start;
    private JButton replay;
    private JTextField nameInput;
    private String name;
    
    private JPanel gridPanel;
    private JPanel northSuperPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    
    private Random random = new Random();

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        topText = new JLabel();
        topText2 = new JLabel();
        gridButtons = new JButton[9];
        start = new JButton("Start");
        replay = new JButton("Re-play");
        nameInput = new JTextField();
        
        gridPanel = new JPanel();
        northSuperPanel = new JPanel();
        northPanel = new JPanel();
        eastPanel = new JPanel();
        southPanel = new JPanel();
        
        random = new Random();
        
        makeFrame();
    }

    /**
     * method to create Frame of GUI
     *
     * 
     */
    public void makeFrame()
    {
        frame = new JFrame("Tic-Tac-Toe");
        Container contentPane = frame.getContentPane();
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        
        //north of borderlayout (text)
        topText.setText("Welcome! What's your name?");
        topText.setHorizontalAlignment(JLabel.CENTER);
        topText2.setHorizontalAlignment(JLabel.CENTER);
        topText.setFont(new Font("Calibri",Font.PLAIN,20));
        topText2.setFont(new Font("Calibri",Font.PLAIN,20));
        topText.setForeground(new Color(0,0,255));
        topText2.setForeground(new Color(0,0,255));
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.add(topText);
        northPanel.add(topText2);
        northSuperPanel.add(northPanel);
        frame.add(northSuperPanel, BorderLayout.NORTH);
        
        //center of borderlayout (button grid)
        gridPanel.setLayout(new GridLayout(3,3));
        for (int x = 0; x<9; x++) {
            gridButtons[x] = new JButton();
            gridButtons[x].setFont(new Font ("Calibri", Font.BOLD, 50));
            gridButtons[x].addActionListener (ev -> gridClick(ev));
            gridPanel.add(gridButtons[x]);
            gridButtons[x].setEnabled(false);
        }
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(gridPanel, BorderLayout.CENTER);
        
        //east of borderlayout (start / re-play)
        eastPanel.setLayout(new GridLayout(6,1));
        
        start.setFont(new Font("Calibri",Font.PLAIN,20));
        start.setBackground(new Color(0,255,0));
        start.addActionListener (ev -> start());
        
        replay.setFont(new Font("Calibri",Font.PLAIN,20));
        replay.setBackground(new Color(128,128,128));
        replay.addActionListener (ev -> replay());
        replay.setEnabled(false);
        
        eastPanel.add(Box.createVerticalStrut(100));
        eastPanel.add(start);
        eastPanel.add(Box.createVerticalStrut(100));
        eastPanel.add(Box.createVerticalStrut(100));
        eastPanel.add(replay);
        eastPanel.add(Box.createVerticalStrut(100));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(40,20,20,20));
        frame.add(eastPanel, BorderLayout.EAST);
        
        //south of borderlayout (input text)
        nameInput.setText("Please put your name here");
        nameInput.setFont(new Font("Calibri",Font.PLAIN,20));
        nameInput.setPreferredSize(new Dimension(480, 40));
        southPanel.add(nameInput);
        frame.add(southPanel, BorderLayout.SOUTH);
        
        makeMenu();
        frame.setVisible(true);
    }
    
    /**
     * method to create menu for GUI
     * 
     */
    public void makeMenu()
    {
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        //menu
        JMenu menu = new JMenu ("Menu");
        menu.setFont(new Font("Calibri",Font.PLAIN,16));
        menubar.add(menu);
        
        //menu items and action listeners
        JMenuItem reset = new JMenuItem("Reset");
        reset.setFont(new Font("Calibri",Font.PLAIN,16));
        reset.addActionListener(ev -> reset());
        
        JMenuItem quit = new JMenuItem("Quit");
        quit.setFont(new Font("Calibri",Font.PLAIN,16));
        quit.addActionListener(ev -> System.exit(0));
        
        menu.add(reset);
        menu.add(quit);
    }
    
    /**
     * event for start button
     * 
     */
    private void start()
    {
        if (nameInput.getText().equals("")){
            //text change
            topText.setText("What's your name again?");
            northPanel.add(topText);
            northPanel.add(topText2);
            northSuperPanel.add(northPanel);
            frame.add(northSuperPanel, BorderLayout.NORTH);
            
        }
        else{
            //text change
            name = nameInput.getText();
            topText.setText(name + ", please place a nought.");
            topText2.setText("AI will place a cross after you.");
            northPanel.add(topText);
            northPanel.add(topText2);
            northSuperPanel.add(northPanel);
            frame.add(northSuperPanel, BorderLayout.NORTH);
            
            //bg color change
            start.setBackground(new Color(128,128,128));
            
            //disable button
            start.setEnabled(false);
            
            //enable tic-tac-toe
            for (int x=0; x<9; x++){
                gridButtons[x].setEnabled(true);
            }
            
        }
    }
    
    /**
     * event for replay button
     * 
     */
    private void replay()
    {
        //clear grid 
        for (int x = 0; x<9; x++) {
            gridButtons[x].setText("");
            gridButtons[x].setEnabled(true);
        }
        
        //bg color change
        replay.setBackground(new Color(128,128,128));
        
        //disable button
        replay.setEnabled(false);
        
        //replace topText & topText2
        topText.setText(name + ", please place a nought.");
        topText2.setText("AI will place a cross after you.");
        northPanel.add(topText);
        northPanel.add(topText2);
        northSuperPanel.add(northPanel);
        frame.add(northSuperPanel, BorderLayout.NORTH);
        
    }

    /**
     * event for reset button
     * 
     */
    private void reset()
    {
        //clear grid and disable
        for (int x = 0; x<9; x++) {
            gridButtons[x].setText("");
            gridButtons[x].setEnabled(false);
        }
        
        //clear name and re-enable start
        name = "";
        start.setBackground(new Color(0,255,0));
        start.setEnabled(true);
        
        //disable replay
        replay.setEnabled(false);
        replay.setBackground(new Color(128,128,128));
        
        //reset topText & topText2
        topText.setText("Welcome! What's your name?");
        topText2.setText("");
        northPanel.add(topText);
        northPanel.add(topText2);
        northSuperPanel.add(northPanel);
        frame.add(northSuperPanel, BorderLayout.NORTH);
        
        //reset nameInput
        nameInput.setText("Please put your name here");
        southPanel.add(nameInput);
        frame.add(southPanel, BorderLayout.SOUTH);
        
    }    
    
    /**
     * event for grid buttons
     * 
     * @param ActionEvent e - takes an action event as parameter
     * 
     */
    private void gridClick(ActionEvent e)
    {
        for (int x=0; x<9; x++){
            if (e.getSource() == gridButtons[x]){
                if (gridButtons[x].getText().equals("")){
                    gridButtons[x].setText("O");
                    winCheck();
                    tieCheck();
                    AImove();
                }
            }
        }
        replay.setEnabled(true);
        replay.setBackground(new Color(0,255,0));
                
    }
    
    /**
     * AI movement (random)
     * 
     */
    private void AImove()
    {
        int r = random.nextInt(8);
        int i = 0 ;
        boolean b = true;
        
        while (b) {
            for (int x=0; x<9; x++){
                if (gridButtons[x].getText().equals("")){
                    i++;
                }
            }
            b = false;
        }
        while (!(gridButtons[r].getText().equals("")) && i > 1){
            r = random.nextInt(8);      //reroll till we find an empty text button
        }
        
        gridButtons[r].setText("X");
        winCheck();
        
    }
    
    private void winCheck()
    {
        //Player (O) win conditions
        if ((gridButtons[0].getText().equals("O")) &&
            (gridButtons[1].getText().equals("O")) &&
            (gridButtons[2].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[3].getText().equals("O")) &&
            (gridButtons[4].getText().equals("O")) &&
            (gridButtons[5].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[6].getText().equals("O")) &&
            (gridButtons[7].getText().equals("O")) &&
            (gridButtons[8].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[0].getText().equals("O")) &&
            (gridButtons[3].getText().equals("O")) &&
            (gridButtons[6].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[1].getText().equals("O")) &&
            (gridButtons[4].getText().equals("O")) &&
            (gridButtons[7].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[2].getText().equals("O")) &&
            (gridButtons[5].getText().equals("O")) &&
            (gridButtons[8].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[0].getText().equals("O")) &&
            (gridButtons[4].getText().equals("O")) &&
            (gridButtons[8].getText().equals("O"))){
            playerWin(); 
        }
        if ((gridButtons[2].getText().equals("O")) &&
            (gridButtons[4].getText().equals("O")) &&
            (gridButtons[6].getText().equals("O"))){
            playerWin(); 
        }
        
        //AI (X) win conditions
        if ((gridButtons[0].getText().equals("X")) &&
            (gridButtons[1].getText().equals("X")) &&
            (gridButtons[2].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[3].getText().equals("X")) &&
            (gridButtons[4].getText().equals("X")) &&
            (gridButtons[5].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[6].getText().equals("X")) &&
            (gridButtons[7].getText().equals("X")) &&
            (gridButtons[8].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[0].getText().equals("X")) &&
            (gridButtons[3].getText().equals("X")) &&
            (gridButtons[6].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[1].getText().equals("X")) &&
            (gridButtons[4].getText().equals("X")) &&
            (gridButtons[7].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[2].getText().equals("X")) &&
            (gridButtons[5].getText().equals("X")) &&
            (gridButtons[8].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[0].getText().equals("X")) &&
            (gridButtons[4].getText().equals("X")) &&
            (gridButtons[8].getText().equals("X"))){
            AIwin();
        }
        if ((gridButtons[2].getText().equals("X")) &&
            (gridButtons[4].getText().equals("X")) &&
            (gridButtons[6].getText().equals("X"))){
            AIwin();
        }
        
    }
    
    private void tieCheck()
    {
        //check for any empty gridButtons
        int i = 0;
        for (int x = 0; x<9; x++) {
            if (gridButtons[x].getText().equals("")){
                i++;
            }
        }
        
        if (i == 0) {
            tie();
        }
    }
    
    private void playerWin() 
    {
        topText.setText("You win! Congratulations, " + name + "!");
        topText2.setText("");
        northPanel.add(topText);
        northPanel.add(topText2);
        northSuperPanel.add(northPanel);
        frame.add(northSuperPanel, BorderLayout.NORTH);
        for (int x = 0; x<9; x++) {
            gridButtons[x].setEnabled(false);
        }
    }
    
    private void AIwin()
    {
        topText.setText("AI wins! Do better next time " + name + "!");
        topText2.setText("");
        northPanel.add(topText);
        northPanel.add(topText2);
        northSuperPanel.add(northPanel);
        frame.add(northSuperPanel, BorderLayout.NORTH);
        for (int x = 0; x<9; x++) {
            gridButtons[x].setEnabled(false);
        }
    }
    
    private void tie()
    {
        topText.setText("Neck-and-neck! Good job, " + name + "!");
        topText2.setText("");
        northPanel.add(topText);
        northPanel.add(topText2);
        northSuperPanel.add(northPanel);
        frame.add(northSuperPanel, BorderLayout.NORTH);
        for (int x = 0; x<9; x++) {
            gridButtons[x].setEnabled(false);
        }
    }

}
