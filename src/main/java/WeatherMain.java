import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class WeatherMain {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String[] args) {
        WeatherMain main = new WeatherMain();
        String fileName = "datamunging/weather.dat";
        System.out.println("Resource filename: " + fileName);
        try {
            File file = main.getFileFromResource(fileName);
            List<String> data = printAndGetFileContent(file);
            if (data != null && !data.isEmpty()) {
                double minDiff = Double.MAX_VALUE;
                int minDiffRow = 0;
                for(String line : data) {
                    System.out.println("line:" + line);
                    if(!line.isBlank()) {
                        String[] array = line.split("\\s+");
                        if(main.isNumeric(array[1]) && main.isNumeric(array[2]) && main.isNumeric(array[3])) {
                            int row = Integer.valueOf(array[1]);
                            double mxT = Double.valueOf(array[2]);
                            double mnT = Double.valueOf(array[3]);
                            double diff = mxT - mnT;
                            if (diff < minDiff) {
                                minDiff = diff;
                                minDiffRow = row;
                            }
                        } else {
                            System.out.println("Wrong dataline found : " + line);
                        }
                    }
                }
                System.out.println("Result minDiffRow: " + minDiffRow );
            } else {
                System.out.println("No data found ! (" + fileName +")");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private static List<String> printAndGetFileContent(File file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}