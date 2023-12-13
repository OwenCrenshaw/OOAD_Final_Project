import javax.security.auth.login.AccountExpiredException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Game_Board {

    private final List<Card> cards;
    //list of cards from the card class

    private final int board_size; // this will hold the board size for later
    private int turns; // how many turns are we into the game
    private int matches_found; // how many matches do we have
    private int selected_card_idx; // this will hold the first card indx chosen
    private boolean no_match; // is there a match

    public Game_Board(int board_size){ // constructor
        cards = new ArrayList<>(); // all the variables being intialized
        initialize_cards(board_size);
        this.board_size = board_size;
        this.turns = 0;
        this.matches_found = 0;
        selected_card_idx = -1;
        no_match = false;

    }

    private void initialize_cards(int board_size){ // this is making the (board size x board) size game
        //I need to make pairs of cards with values
        for (int i = 1; i <= board_size / 2; i++) { // need to use this when changing levels
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
        Collections.shuffle(cards); //shuffle the cards
    }
    public Card getCard(int idx){ //was private

        return cards.get(idx); //get the value of the cards depending on the list idx
    }
    public void flipCard(int index) { //uses card flip  in card class to flip the card
        Card card = cards.get(index); //get the current card
        if (!card.is_face_up()) { // flip on click
            card.flip();
            if (selected_card_idx == -1) { // this is if this is the first flip
                selected_card_idx = index;
            } else { //second card so check match
                no_match = false;
                checkMatch(index); //check match
                turns++;
                selected_card_idx = -1;
            }
        }
    }
    public void checkMatch(int idx){ //check if the cards are matching in value
        Card firstCard = cards.get(selected_card_idx);
        Card secondCard = cards.get(idx); //get the values
        if (firstCard.getCard_val() == secondCard.getCard_val()){
            matches_found++;


        }else {//flip the cards back to the face down state //no match
            //cardButton.addActionListener(new ActionListener());
           // @Override
            //public void actionPerformed(ActionEvent e){
            firstCard.flip();
            secondCard.flip();
            //update ui here?
            no_match = true;
            //}

        }
        if (matches_found == (board_size /2)){ // this is the game over condition
            //game over here
            //update UI to show this
            System.out.println("Congratulations you have beaten the game!!!");
            int total_game_turns = getTurns();
            System.out.println("total turns is " + total_game_turns);
        }

    }
    public int getTurns(){
        return turns;
    } //return total turns

    public boolean get_is_match(){ // return the no match variable
        return no_match;
    }

}
// maybe use strategy in the scoreboard class
// 3 matches in s row might tbe worth more points rather than 2 in a row


// for the database use a file write...might be easier