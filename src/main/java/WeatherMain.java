import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WeatherMain
{
    public static void main(String[] args) {
        WeatherMain main = new WeatherMain();
        String fileName = "datamunging/weather.dat";
        System.out.println("Resource filename: " + fileName);
        try {
            File file = main.getFileFromResource(fileName);
            List<String> data = printAndGetFileContent(file);
            if (data != null) {
                
                //TODO feldolgozás
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
}