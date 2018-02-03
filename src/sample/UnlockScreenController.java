package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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

    @FXML
    private Circle c1, c2, c3, c4, c5, c6, c7, c8, c9;

    private ArrayList<Circle> circles;

    private ArrayList<Circle> userPattern;

    private ArrayList<Line> lines;

    public ArrayList<String[]> patterns;
    public String[] selectedPattern;


    private String file = "patterns.txt";

    public void initialize(){
        loadPatternDataFromFileToList();
        selectPattern();
        showRequestedPattern();
        createCircleList();

        patternPane.setOnMouseDragged(e->{
            if(userPattern == null) userPattern = new ArrayList<>();
            checkCollision(e.getX(), e.getY());
        });

        patternPane.setOnMouseReleased(e->{
            boolean result = checkUserPattern(userPattern);
            if(result){
                try {
                    loadGameSheet();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else {
                removeLines();
            }
        });
    }

    private void removeLines() {

        if(lines != null) {
            for (Line l : lines) {
                patternPane.getChildren().remove(l);
            }
        }
    }

    private void createCircleList() {
        circles = new ArrayList<>();
        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        circles.add(c4);
        circles.add(c5);
        circles.add(c6);
        circles.add(c7);
        circles.add(c8);
        circles.add(c9);

    }

    private void checkCollision(double x, double y) {
        for(Circle c : circles){
            if((c.getCenterX() + c.getRadius()) > x && (c.getCenterX() - c.getRadius()) < x){
                if((c.getCenterY() + c.getRadius()) > y && (c.getCenterY() - c.getRadius()) < y){
                    userPattern.add(c);
                    System.out.println("COLLISION");
                    drawLine();
                }
            }
        }

    }

    private void drawLine() {
        if(userPattern.size() > 1){
            int size = userPattern.size();
            int index1 = size - 2;
            int index2 = size - 1;

            draw(userPattern.get(index1), userPattern.get(index2));
        }
    }

    private void draw(Circle start, Circle end) {
        if(lines == null) lines = new ArrayList<>();

        Line line = new Line();

        line.setStartX(start.getCenterX());
        line.setStartY(start.getCenterY());
        line.setEndX(end.getCenterX());
        line.setEndY(end.getCenterY());

        line.setStroke(Color.WHITE);

        patternPane.getChildren().add(line);
        lines.add(line);
    }

    private void loadPatternDataFromFileToList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            patterns = new ArrayList<>();

            while(line != null){
                String[] splittedLine = line.split(" ");

                patterns.add(splittedLine);
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

        for(String i: selectedPattern){
            pattern += i + " ";
        }
       // lRequestedPattern.setText(pattern);
    }

    private boolean checkUserPattern(ArrayList<Circle> userPattern){

        if(userPattern.size() != selectedPattern.length){
            return false;
        }

        for(int i = 0; i<userPattern.size(); i++){
            if(!userPattern.get(i).getId().equals(selectedPattern[i])){
                return false;
            }
        }

        return true;
    }

    private void loadGameSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        rootPane.getChildren().setAll(pane);
    }


}
