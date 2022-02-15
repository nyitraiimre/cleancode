package hu.xxx.datamunging.footbal;

import hu.xxx.datamunging.dto.FootbalTeam;
import hu.xxx.datamunging.util.FileLineParser;
import hu.xxx.datamunging.util.FileReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Footbal {

    public FootbalTeam getWinnerTeam(String resourceFile) {
        FootbalTeam result = null;
        try {
            List<String> dataLines = new FileReader().readFileLines(resourceFile);
            FileLineParser fileLineParser = new FileLineParser();
            List<FootbalTeam> footbalTeams = new ArrayList<>();
            for(String line : dataLines) {
                String[] teamArray = fileLineParser.parseDataLine(line);
                if(teamArray != null) {
                    FootbalTeam footbalTeam = FootbalTeam.of(teamArray);
                    if(footbalTeam != null) {
                        footbalTeams.add(FootbalTeam.of(teamArray));
                    }
                }
            }
            result = findTeamWithMaxGoalDiff(footbalTeams);
            System.out.println("Result team name: " + result.getName());
        } catch (Exception  e) {
            e.printStackTrace();
            throw new IllegalStateException("Findig hu.xxx.datamunging.footbal team failed!",e);
        }
        return result;
    }

    private FootbalTeam findTeamWithMaxGoalDiff(List<FootbalTeam> footbalTeams) {
        return footbalTeams
                .stream()
                .max(Comparator.comparing(FootbalTeam::getGoalDiff))
                .orElseThrow(NoSuchElementException::new);
    }

}