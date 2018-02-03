package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreboardController {

    private ArrayList<Long> results;

    public void initialize() throws IOException {
        results = ScoreSaverAndReader.read();
        Collections.sort(results);
        showResults();
    }

    private void showResults() {
        for(int i = results.size()-1; i>=0; i--){
            //DO stuff
        }
    }
}
