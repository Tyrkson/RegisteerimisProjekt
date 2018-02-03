package sample;


import java.io.*;
import java.util.ArrayList;

public class ScoreSaverAndReader {

    private ScoreSaverAndReader(){}

    public static void save(int time) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt", true));

        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        bw.close();

    }

    public static ArrayList<Integer> read() throws IOException {
        BufferedReader br =  new BufferedReader(new FileReader("results.txt"));

        String line = br.readLine();

        ArrayList<Integer> results = new ArrayList<>();

        while(line != null){
            results.add(Integer.parseInt(line));
            line = br.readLine();
        }

        return results;
    }
}
