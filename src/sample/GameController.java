package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;

public class GameController {

    private ArrayList<String> laused;

    private String selectedLause;

    private String file = "andmed.txt";

    Timeline inputPaneAnimation;

    private int globalPace = -15;

    @FXML
    Label lRequestedInput, lRound, lTime;

    @FXML
    TextField TfInput;

    @FXML
    AnchorPane rootPane;

    @FXML
    public void checkUserInput() throws IOException {
        if(TfInput.getText().toString().equals(selectedLause)){
            Game.finishedRound();
            if(Game.isOver()){
                loadMainMenuSheet();
            }else{
                startANewRound();
            }
        }
    }

    private void startANewRound() throws IOException {
        Game.newRound();
        loadUnlockScreenSheet();
    }

    private void loadUnlockScreenSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("unlock_screen.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private void loadMainMenuSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mein_menu.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    public void initialize(){
        loadDataFromFileToList();
        selectData();
        showRequestInput();
        updateRoundLabel();
    }

    private void updateRoundLabel() {
        int finishedRounds = Game.getFinishedRounds();
        int roundsToPlay = Game.getRoundsToPlay();
        lRound.setText(finishedRounds + "/" + roundsToPlay);
    }


    private void showRequestInput() {
        lRequestedInput.setText(selectedLause);
    }

    private void selectData() {
        selectedLause = laused.remove(RandomSelector.select(laused.size()));
    }

    private void loadDataFromFileToList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            laused = new ArrayList<>();

            while(line != null){
                laused.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            laused = new ArrayList<>();
        } catch (IOException e) {
            laused = new ArrayList<>();
        }

    }

    @FXML
    private Pane inputPane;

    @FXML
    private AnchorPane gamePane;

    @FXML
    public void executeInputPaneAnimation(){

        if(inputPaneAnimation != null && inputPaneAnimation.getStatus() == Animation.Status.RUNNING){
            stopInputPaneAnimation();
        }

        globalPace *= -1;

        changeInputPaneState();
    }

    private void changeInputPaneState() {
        inputPaneAnimation = new Timeline(new KeyFrame(Duration.millis(15), e->{
            double newWidth = inputPane.getWidth() + globalPace;
            inputPane.setMinWidth(newWidth);

            if(newWidth >= gamePane.getWidth() || newWidth <= 0){
                if(newWidth > gamePane.getWidth()){
                    inputPane.setMinWidth(gamePane.getWidth());
                }
                if(newWidth < 0){
                    inputPane.setMinWidth(0);
                }
                stopInputPaneAnimation();
            }
        }));
        inputPaneAnimation.setCycleCount(Animation.INDEFINITE);
        inputPaneAnimation.play();
    }

    private void stopInputPaneAnimation() {
        inputPaneAnimation.stop();
    }


}
