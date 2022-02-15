package hu.xxx.datamunging.weather;

import hu.xxx.datamunging.dto.WeatherItem;
import hu.xxx.datamunging.util.FileLineParser;
import hu.xxx.datamunging.util.FileReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Weather {

    public WeatherItem getWinnerTeam(String resourceFile) {
        WeatherItem result = null;
        try {
            List<String> dataLines = new FileReader().readFileLines(resourceFile);
            FileLineParser fileLineParser = new FileLineParser();
            List<WeatherItem> weatherItems = new ArrayList<>();
            for(String line : dataLines) {
                String[] weatherArray = fileLineParser.parseDataLine(line);
                if(weatherArray != null) {
                    if(WeatherMain.isNumeric(weatherArray[1]) && WeatherMain.isNumeric(weatherArray[2]) && WeatherMain.isNumeric(weatherArray[3])) {
                        double mxT = Double.valueOf(weatherArray[2]);
                        double mnT = Double.valueOf(weatherArray[3]);
                        weatherItems.add(new WeatherItem(Integer.valueOf(weatherArray[1]), mxT, mnT, mxT - mnT));
                    }
                }
            }
            result = findTeamWithMinGoalDiff(weatherItems);
            System.out.println("Result team name: " + result.getRowNumber());
        } catch (Exception  e) {
            e.printStackTrace();
            throw new IllegalStateException("Findig hu.xxx.datamunging.weather  failed!",e);
        }
        return result;
    }

    private WeatherItem findTeamWithMinGoalDiff(List<WeatherItem> weathers) {
        return weathers
                .stream()
                .min(Comparator.comparing(WeatherItem::getTempDiff))
                .orElseThrow(NoSuchElementException::new);
    }

}