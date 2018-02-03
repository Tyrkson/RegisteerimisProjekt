package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    public AnchorPane rootPane;

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
        String[] keys = (String[]) userData.keySet().toArray();
        key = keys[index];
        username = userData.get(key);
    }

    private void showUserInput() {
        lRequestedInput1.setText(username);
        lRequestedInput2.setText(key);
    }

    @FXML
    public void checkUserInput() throws IOException {
        if(tfUsername.getText().equals(lRequestedInput1) &&
                tfPassword.getText().equals(lRequestedInput2)){
            loadGameSheet();
        }
    }

    private void loadGameSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
