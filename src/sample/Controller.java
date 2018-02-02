package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private ArrayList<String> laused;

    private String selectedLause;

    private String file = "andmed.txt";

    @FXML
    Label LRequestedInput, LResult;

    @FXML
    TextField TfInput;

    @FXML
    public void checkUserInput(){
        if(TfInput.getText().toString().equals(selectedLause)){
            showResult("Õige");
        }else{
            showResult("Vale");
        }
        selectData();
        showRequestInput();
    }

    private void showResult(String result) {
        LResult.setText(result);
    }

    public void initialize(){
        loadDataFromFileToList();
        selectData();
        showRequestInput();
    }

    private void showRequestInput() {
        LRequestedInput.setText(selectedLause);
    }

    private void selectData() {
        Random random = new Random();
        int randIndex;

        randIndex = random.nextInt(laused.size()-1);

        selectedLause = laused.remove(randIndex);

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



}
