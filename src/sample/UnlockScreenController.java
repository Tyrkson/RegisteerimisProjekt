package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnlockScreenController {

    @FXML
    public Label lRequestedPattern;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane patternPane;

    public ArrayList<int[]> patterns;
    public int[] selectedPattern;


    private String file = "patterns.txt";

    public void initialize(){
        loadPatternDataFromFileToList();
        selectPattern();
        showRequestedPattern();
    }

    private void loadPatternDataFromFileToList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            patterns = new ArrayList<>();

            while(line != null){
                String[] splittedLine = line.split(" ");
                int[] pattern = new int[splittedLine.length];
                for(int i = 0; i<splittedLine.length; i++){
                    pattern[i] = Integer.valueOf(splittedLine[i]);
                }
                patterns.add(pattern);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            patterns = new ArrayList<>();
        } catch (IOException e) {
            patterns = new ArrayList<>();
        }
    }

    private void selectPattern() {
        selectedPattern = patterns.get(RandomSelector.select(patterns));
    }

    private void showRequestedPattern() {

        String pattern = "";

        for(int i: selectedPattern){
            pattern += i + " ";
        }
        lRequestedPattern.setText(pattern);
    }

    private boolean checkUserPattern(int[] userPattern){
        return userPattern.equals(selectedPattern);
    }

    private void loadGameSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        rootPane.getChildren().setAll(pane);
    }


}
