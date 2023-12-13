import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //using this for when the board is clicked

public class GameTimer extends Timer{
    private int sec_past; // how many seconds have elapsed

    public GameTimer(int delay, ActionListener listener){
        super(delay, listener); // delaying because should not start right  away
        sec_past = 0;
    }
    public int getSec_past(){
        return sec_past;
    } //return the seconds past
    public void incrementSec_Past(){
        sec_past++;
    } //increase the seconds pasts

}
