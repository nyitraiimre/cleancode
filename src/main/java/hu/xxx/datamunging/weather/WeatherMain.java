package hu.xxx.datamunging.weather;

import hu.xxx.datamunging.dto.WeatherItem;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class WeatherMain {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String[] args) {
        WeatherMain weatherMain = new WeatherMain();
        String fileName = "datamunging/weather.dat";
        System.out.println("Resource filename: " + fileName);
        try {
            File file = weatherMain.getFileFromResource(fileName);
            List<String> dataLines = weatherMain.printAndGetFileContent(file);
            List<WeatherItem> weatherItems = weatherMain.parseDataLines(dataLines);
            WeatherItem  itemWithMinTempDiff = weatherMain.findItemWithMinTempDiff(weatherItems);
            System.out.println("Result itemWithMinTempDiff: " + itemWithMinTempDiff.getRowNumber());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WeatherItem findItemWithMinTempDiff(List<WeatherItem> weatherItems) {
        return weatherItems
                .stream()
                .min(Comparator.comparing(WeatherItem::getTempDiff))
                .orElseThrow(NoSuchElementException::new);
    }

    private List<WeatherItem> parseDataLines(List<String> dataLines) {
        List<WeatherItem> weatherItems = new ArrayList<>();
        for(String line : dataLines) {
            if(!line.isBlank()) {
                String[] array = line.split("\\s+");
                if(WeatherMain.isNumeric(array[1]) && WeatherMain.isNumeric(array[2]) && WeatherMain.isNumeric(array[3])) {
                    double mxT = Double.valueOf(array[2]);
                    double mnT = Double.valueOf(array[3]);
                    weatherItems.add(new WeatherItem(Integer.valueOf(array[1]), mxT, mnT, mxT - mnT));
                } else {
                    System.out.println("Wrong dataline found : " + line);
                }
            }
        }
        return weatherItems;
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

    private List<String> printAndGetFileContent(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        lines.forEach(System.out::println);
        return lines;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}