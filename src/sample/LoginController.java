package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LoginController {


    @FXML
    public Label lRequestedInput1, lRequestedInput2;

    @FXML
    public TextField tfUsername, tfPassword;

    @FXML
    public AnchorPane rootPane, loginPane;

    private HashMap<String, String> userData;

    private String key, username;

    private String file = "userData.txt";

    public void initialize(){
        loadDataFromFileToHashMap();
        selectData();
        showUserInput();

    }

    private void loadDataFromFileToHashMap(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            userData = new HashMap<>();

            while(line != null){
                String[] splittedLine = line.split(":");

                userData.put(splittedLine[0], splittedLine[1]);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            userData = new HashMap<>();
        } catch (IOException e) {
            userData = new HashMap<>();
        }

    }

    private void selectData() {
        int index= RandomSelector.select(userData.size());
        String[] keys =  userData.keySet().toArray(new String[userData.size()]);
        key = keys[index];
        username = userData.get(key);
    }

    private void showUserInput() {
        lRequestedInput1.setText(username);
        lRequestedInput2.setText(key);
    }

    @FXML
    public void checkUserInput() throws IOException {
        if(tfUsername.getText().equals(lRequestedInput1.getText()) &&
                tfPassword.getText().equals(lRequestedInput2.getText())){
            loadGameSheet();
        }
    }

    Timeline inputPaneAnimation;
    public int globalPace = -15;

    @FXML
    public AnchorPane inputPane;

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

            if(newWidth >= loginPane.getWidth() || newWidth <= 0){
                if(newWidth > loginPane.getWidth()){
                    inputPane.setMinWidth(loginPane.getWidth());
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

    private void loadGameSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
