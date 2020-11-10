import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * A class representing game called "Chasing bombs".
 * The aim of the game is for a player to click on a square and hopefully avoid a bomb that is hidden under the square. 
 * The class provides graphical user interface for the game.
 * It allows player to gain points, win/lose the game and swap between the difficulty of the game.
 *
 * @author Daniel Bielech
 * @version 12.03.2020
 */
public class ChasingBombs extends JFrame
{      
    private ArrayList<JPanel> squareList = new ArrayList<JPanel>(10); // list representing board of the game
    JLabel bomb = new JLabel(new ImageIcon("bomb-50.png")); // label representing "bomb"
    JLabel playerScore = new JLabel(); // label showing current score of the player
    JLabel currentDifficulty = new JLabel("Difficulty: easy"); // label showing current difficulty
    int score = 0; // current number of points scored by the player 
    int difficulty = 1; // current difficulty (1 - easy, 2 - intermediate, 3 - difficult)
    boolean gameIsActive = false; // tells if the game is active or not 
    
    /**
     * Constructor for objects of class ChasingBombs
     * Run the game and show its GUI on the screen.
     */
    public ChasingBombs()
    {
        super("Chasing bombs"); // frame title
        setSize(850,500); // horizontally, verticall
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeFrame();
        displayWelcomeMessage();
    }      
    
    /**
     * Create the Swing frame and its content.
     * Set all properties and listeners.
     * In other words initialise the GUI for the game. 
     */
    public void makeFrame()
    {
        /////////////////////CONTENT PANE/////////////////////// 
        //Set horizontal box layout for the content pane(the layer that holds all three main panels)
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
               
        /////////////////////PANELS////////////////////////////
        //CREATE PANELS
        JPanel leftPanel = new JPanel(); // left panel - grid layout
        JPanel middlePanel = new JPanel(); // middle panel - box layout
        JPanel rightPanel = new JPanel(); // right panel - box layout
        
        //SET PANELS LAYOUT 
        leftPanel.setLayout(new GridLayout(2,5)); //left panel - 2 rows 5 columns grid layout
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS)); //middle panel - x box layout
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); //right panel - x box layout
      
        //SET PANELS BACKGROUND COLOR
        middlePanel.setBackground(new Color(153, 204, 255));
        rightPanel.setBackground(new Color(204, 153, 255));
        
        //SET PREFERRED AND MAXIMUM SIZE
        leftPanel.setPreferredSize(new Dimension(285,500));
        leftPanel.setMaximumSize(leftPanel.getPreferredSize());
        middlePanel.setPreferredSize(new Dimension(285,500));
        middlePanel.setMaximumSize(middlePanel.getPreferredSize());
        rightPanel.setPreferredSize(new Dimension(285,500));     
        rightPanel.setMaximumSize(rightPanel.getPreferredSize());
         
        //ADD PANELS TO THE CONTENT PANE
        add(leftPanel);
        add(middlePanel);
        add(rightPanel);
           
        /////////////////PANELS CONTENT////////////////////        
        //LEFT PANEL
        //Create the board of the game which is the 5x2 grid.    
        for(int i=0; i<10; i++) {  
            JPanel square = new JPanel();
            square.addMouseListener(new MouseAdapter() { // make square respond to user mouse clicks
                public void mouseClicked(MouseEvent evt) {
                    if(gameIsActive) {
                        JPanel clickedPanel = (JPanel) evt.getSource();
                        if(clickedPanel.getComponentCount() == 1) { // getComponentCount() returns 1 for squares with bomb planted, it's 0 for empty squares
                            clickedPanel.setBackground(new Color(255, 26, 26));
                            bomb.setVisible(true);
                            gameIsActive = false;
                            playerScore.setText("You lost! You got "+score+" points");
                            JOptionPane.showMessageDialog(null,"BOOOOOOOOOOOM!","Explosion",
                            JOptionPane.INFORMATION_MESSAGE,new ImageIcon("bang-50.png"));
                        }
                        else if(clickedPanel.getComponentCount() == 0) {
                            //when the square is clicked and bomb is not there, 
                            //two invisible components will be added to it.
                            //It prevets from scoring multiple points on one square                           
                            score++;
                            clickedPanel.add(new JLabel(""));
                            clickedPanel.add(new JLabel(""));
                            
                            clickedPanel.setBackground(new Color(0, 179, 62));
                            playerScore.setText("Your current score: "+score);
                            
                            switch(difficulty) {
                                case 1:
                                    if(score == 
                            }
                            
                        }   
                    }zz
                }
            });
            square.setBorder(BorderFactory.createLineBorder(Color.black)); //set square's border
            square.setLayout(new BorderLayout()); //set square's layout manager
            leftPanel.add(square); //add square to the left panel
            squareList.add(square); //add square to the collection representing game board (used to access the specific squares later)
        }        
        
        //MIDDLE PANEL
        //create
        JLabel gameOptions = new JLabel("Game options");
        JButton playGame = new JButton("Start new game");
        JButton exitGame = new JButton("Exit");  
        //actions
        playGame.addActionListener(event -> startNewGame());
        exitGame.addActionListener(event -> System.exit(0));        
        //font
        gameOptions.setFont(new Font("Arial", Font.PLAIN, 30));
        playerScore.setFont(new Font("Arial", Font.BOLD, 20));
        //center
        gameOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        playGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        //add
        middlePanel.add(Box.createVerticalStrut(10));
        middlePanel.add(gameOptions);
        middlePanel.add(Box.createVerticalStrut(20));
        middlePanel.add(playGame);
        middlePanel.add(Box.createVerticalStrut(10));
        middlePanel.add(exitGame);
        middlePanel.add(Box.createVerticalStrut(95));
        middlePanel.add(playerScore);
        
        //RIGHT PANEL
        //create
        JLabel chooseDifficulty = new JLabel("Choose difficulty");
        JButton difficultyEasy = new JButton("Easy");
        JButton difficultyIntermediate = new JButton("Intermediate");
        JButton difficultyDifficult = new JButton("Difficult");
        //actions
        difficultyEasy.addActionListener(event -> changeDifficulty(event));
        difficultyIntermediate.addActionListener(event -> changeDifficulty(event));
        difficultyDifficult.addActionListener(event -> changeDifficulty(event));
        //font 
        chooseDifficulty.setFont(new Font("Arial", Font.PLAIN, 30));
        currentDifficulty.setFont(new Font("Arial", Font.BOLD, 10));
        //center
        chooseDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultyEasy.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultyIntermediate.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultyDifficult.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        //add
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(chooseDifficulty);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(difficultyEasy);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(difficultyIntermediate);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(difficultyDifficult);
        rightPanel.add(Box.createVerticalStrut(280));
        rightPanel.add(currentDifficulty);
          
        setVisible(true); // make the GUI visible
    }
    
     /**
     * Plant a "bomb" at a random square.
     * To do this generate random number between 0-9 (inclusive)
     * as game board cosists of 10 squares.
     */
    public void plantBomb()
    {
        Random randomizer = new Random();      
        //bomb.setVisible(false); // make the bomb invisible for the player (main idea of the game)
        int randomNumber = randomizer.nextInt(10);
        squareList.get(randomNumber).add(bomb, BorderLayout.CENTER); // plant the bomb
        
        //Update the component 
        squareList.get(randomNumber).revalidate();
        squareList.get(randomNumber).repaint();
    }
    
    /**
     * Allows to start a new game.
     * Reset the board to the initial state.
     * Plant a new bomb.
     * Reset the score (set the score to 0 and clear the score label).
     * Make the game active.
     * Display appropriate message.
     */
    public void startNewGame()
    {
        //iterate over all squares to remove bomb and change background color to default
        for(JPanel square : squareList) {
            square.removeAll(); // remove bomb and empty components
            square.setBackground(new JPanel().getBackground()); // set default background color
            //Update the component 
            square.revalidate();
            square.repaint();
        }
        
        playerScore.setText(""); //clear the score label
        score = 0; //reset the score
        plantBomb(); // plant new bomb
        gameIsActive = true; //make the game active    
        
        JOptionPane.showMessageDialog(null,
                        "<html>Bomb has been planted.<br>"
                        +"Good luck!</html>","Game has begun",JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("bomb-50.png"));     
    }
    
    /**
     * Display welcoming message
     * that introduces the game rules 
     * and gives instructions to the user.
     */
    public void displayWelcomeMessage()
    {
        JLabel message = new JLabel("<html>The aim of the game is to click on a square<br>" 
        +"and hopefully avoid a bomb that is hidden under it.<br>"  
        +"Each time you click on a square without the bomb, you gain a point.<br>" 
        +"You can choose 3 available difficulty levels (the default is easy):<br>"
        +"[Easy] - score 5 [Intermediate] - score 7 [Difficult] - score 8 ~ to win<br><br>"
        +"Info: you can choose difficulty level at the very beggining<br>"
        +"and each time you win or lose the game.<br><br>"
        +"Author: Daniel Bielech</html>");
                
        JOptionPane.showMessageDialog(null,message,
        "Welcome to Chasing Bombs!",
        JOptionPane.INFORMATION_MESSAGE,
        new ImageIcon("logo.png"));   
    }
    
    /** 
     * Change the difficulty of the game 
     * to the one chosen by the player.
     * Update currentDifficulty label.
     * Display appropriate message.
     * Works only if the game is inactive.
     */
    public void changeDifficulty(ActionEvent event)
    {
        if(!gameIsActive) {
            switch(event.getActionCommand()) {
                case "Easy":
                    if(difficulty != 1) {
                        difficulty = 1;
                        currentDifficulty.setText("Difficulty: easy");
                        JOptionPane.showMessageDialog(null,
                        "<html>Difficulty changed to: [Easy]<br>"
                        +"Score 5 points to win the game.</html>");
                    }
                    break;
                case "Intermediate":
                    if(difficulty != 2) {
                        difficulty = 2;
                        currentDifficulty.setText("Difficulty: intermediate");
                        JOptionPane.showMessageDialog(null,
                        "<html>Difficulty changed to: [Intermediate]<br>"
                        +"Score 7 points to win the game.</html>");
                    }
                    break;
                case "Difficult":
                    if(difficulty != 3) {
                        difficulty = 3;
                        currentDifficulty.setText("Difficulty: difficult");
                        JOptionPane.showMessageDialog(null,
                        "<html>Difficulty changed to: [Difficult]<br>"
                        +"Score 8 points to win the game.</html>");
                    }
                    break;
            }
        }    
    }
}