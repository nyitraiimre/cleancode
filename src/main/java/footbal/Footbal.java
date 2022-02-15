package footbal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class Footbal {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public FootbalTeam getWinnerTeam(String resourceFile) {
        FootbalTeam result = null;
        try {
            File file = getFileFromResource(resourceFile);
            List<String> dataLines = printAndGetFileContent(file);
            List<FootbalTeam> footbalTeams = parseDataLines(dataLines);
            result = findTeamWithMaxGoalDiff(footbalTeams);
            System.out.println("Result team name: " + result.getName());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Findig footbal team failed!",e);
        }
        return result;
    }

    private FootbalTeam findTeamWithMaxGoalDiff(List<FootbalTeam> footbalTeams) {
        return footbalTeams
                .stream()
                .max(Comparator.comparing(FootbalTeam::getGoalDiff))
                .orElseThrow(NoSuchElementException::new);
    }

    private List<FootbalTeam> parseDataLines(List<String> dataLines) {
        List<FootbalTeam> footbalTeams = new ArrayList<>();
        for(String line : dataLines) {
            String[] teamDataArray = checkAndGetDataLineData(line);
            if(teamDataArray != null) {
               footbalTeams.add(new FootbalTeam(teamDataArray[2], Integer.valueOf(teamDataArray[7]), Integer.valueOf(teamDataArray[9])));
            }
        }
        return footbalTeams;
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
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        lines.forEach(System.out::println);
        return lines;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static String[] checkAndGetDataLineData(String dataline) {
        String[] ret = null;
        if (dataline == null || dataline.isBlank()) {
            ret = null;
        } else {
            String[] array = dataline.split("\\s+");
            for (int i = 0; i < array.length; i++) {
                System.out.print("|[" + i + "]" + array[i]);
            }
            System.out.println();
            if (array.length > 9 && Footbal.isNumeric(array[7]) && Footbal.isNumeric(array[9])) {
                ret = array;
            } else {
                System.out.println("Wrong dataline found : " + dataline);
            }
        }
        return ret;
    }
}