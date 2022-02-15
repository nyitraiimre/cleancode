package footbal;

import java.util.regex.Pattern;

public class Main {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String[] args) {
        Footbal footbal = new Footbal();
        String fileName = "datamunging/football.dat";
        System.out.println("Resource filename: " + fileName);
        //TODO impl
    }


}