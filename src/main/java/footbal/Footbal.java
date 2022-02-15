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
        //TODO impl
        return new FootbalTeam("x",0,0);
    }

    private FootbalTeam findTeamWithMinGoalDiff(List<FootbalTeam> footbalTeams) {
        return footbalTeams
                .stream()
                .min(Comparator.comparing(FootbalTeam::getGoalDiff))
                .orElseThrow(NoSuchElementException::new);
    }

    private List<FootbalTeam> parseDataLines(List<String> dataLines) {
        List<FootbalTeam> footbalTeams = new ArrayList<>();
        for(String line : dataLines) {
            if(!line.isBlank()) {
                String[] array = line.split("\\s+");
                /*if(FootbalTeam.isNumeric(array[x]) && FootbalTeam.isNumeric(array[x])) {
                    double givenGoals = Double.valueOf(array[2]);
                    double receivedGoals = Double.valueOf(array[3]);
                    footbalTeams.add(new FootbalTeam(array[1]), givenGoals, receivedGoals);
                } else {
                    System.out.println("Wrong dataline found : " + line);
                }*/
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