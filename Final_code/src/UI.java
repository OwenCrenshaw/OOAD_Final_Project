import javax.swing.*;
import java.awt.*; // need these imports to run the UI and the buttons/timer/scoreboard
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class UI extends JFrame{ // need this to call buttons and UI
    private final Game_Board gameBoard; // call in all my classes and needed time variables
    private Scoreboard scoreboard;
    private GameTimer gameTimer;
    private boolean time_go;
    private JLabel timerLabel;

    private JButton firstFlippedButton;
    public UI(){ //constructor
        setTitle("Memory Match Master"); //sets the title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //kill the game when window closes

//        setLayout(new GridLayout(4,4));
//        gameBoard = new Game_Board( 16);
//        initializeUI();
//old stuff



        // create a border layout for the main UI
        setLayout(new BorderLayout());

        // create a panel for the game board with a 4x4 grid layout
        JPanel gamePanel = new JPanel(new GridLayout(4, 4));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Border around the game board
        add(gamePanel, BorderLayout.CENTER);//factory pattern?

        gameBoard = new Game_Board(16); // create a game board with 16 cards

        initializeUI(gamePanel); // Initializing the UI panel

        // create a panel for the scoreboard
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Border for scoreboard panel

        // create a label for the scoreboard timer
        timerLabel = new JLabel("00:00");
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scorePanel.add(timerLabel, BorderLayout.CENTER);

        add(scorePanel, BorderLayout.NORTH); // go top right

        // creat scoreboard
        Scoreboard scoreboard = new Scoreboard();
       //need to show prev scores somehow


        // create the game timer with a delay of 1000 ms
        gameTimer = new GameTimer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTimerTick();
            } // actually start the timer
        });



        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initializeUI(JPanel gamePanel) {
        for (int i = 0; i < 16; i++) { // lop to create 16 buttons ...this need to be changes for different sizes of grid
            JButton cardButton = new JButton("Card " + (i + 1)); //create 16 cards
            gamePanel.add(cardButton); //add them to the panel

            int index = i; // need to make a final

            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { // when the card is clicked start timer
                    if(!time_go){
                        startTime(); //call starttime
                        time_go = true;
                    }
                    gameBoard.flipCard(index); //flip the card

                    updateButtonState(cardButton, index);//This will update the state of the button when clicked
                    if (gameBoard.get_is_match()){ // trying to change the cards back to original state when there is no match
                        reverse_updateButtonState(cardButton, index); // reverse of update
                    }
                }
            });


        }
    }
    public void startTime(){
        gameTimer.start();
    } // call gametimer

    private void gameTimerTick() { // tick the clock
        gameTimer.setDelay(1000); // delay 1000 ms
        timerLabel.setText(String.format("%02d:%02d", gameTimer.getSec_past() / 60, gameTimer.getSec_past() % 60));
        gameTimer.incrementSec_Past();//just added to test if this helps timer go
        //////////////////Above line is what fixed the timer!!!
        gameTimer.restart();
    }
    // update button text for each card state
    private void updateButtonState(JButton button, int index) {
        Card card = gameBoard.getCard(index); // get the card
        if (card.is_face_up()) { // is up?
            button.setText(String.valueOf(card.getCard_val())); // change text to the value of card
        } else {
            button.setText("Card " + (index + 1)); // change text back to og state
        }
    }
    private void reverse_updateButtonState(JButton button, int index) {
        Card card = gameBoard.getCard(index);//get the card
        if (card.is_face_up()) { //is up?
            button.setText(String.valueOf(card.getCard_val())); // change text to the value of card
        } else {
            button.setText("Card " + (index + 1)); // change text back to og state
        }
    }




    public static void main(String[] args) { // this runs the game
        SwingUtilities.invokeLater(UI::new);
    }
}
// the UI will call the observer class in here to get updates from and reports on game status

