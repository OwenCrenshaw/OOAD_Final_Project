public class Card {
    private final int card_val; // this will hold the card value
    private boolean is_face_up; // this is the boolean of if the card is face up
    //attributes
    public Card(int value){ // constructor
        this.card_val = value;
        this.is_face_up = false;
    }//constructor

    public int getCard_val(){ // getting the card value
        return card_val;
    } // get the card value of the card
    public boolean is_face_up(){ // getting the face up value
        return is_face_up;
    //checks if the card is face up
    }
    public void flip(){
        is_face_up = !is_face_up;
    }// flips the card
}
