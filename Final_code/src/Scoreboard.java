import java.util.List;
import java.util.ArrayList;
public class Scoreboard { // going to be in the top left of the UI
    private final List<Integer> scores; // has to have a list of scores

    public Scoreboard(){ // constructor
        scores = new ArrayList<>();

    }
    public void add_score(int score){
        scores.add(score);
    } // add a score to the list
    public List<Integer> getScores(){
        return scores;
    } // grab the list of scores
}
// This is where the scoreboard strategy will go
