package hu.xxx.datamunging.dto;

import hu.xxx.datamunging.util.FileLineParser;

import java.util.Arrays;

public class FootbalTeam {

    private String name;
    private int givenGoal;
    private int receivedGoal;

    public FootbalTeam(String name, int givenGoal, int receivedGoal) {
        this.name = name;
        this.givenGoal = givenGoal;
        this.receivedGoal = receivedGoal;
    }

    public static FootbalTeam of(String[] dataArray) {
        if (dataArray.length > 9 && FileLineParser.isNumeric(dataArray[7]) && FileLineParser.isNumeric(dataArray[9])) {
            return new FootbalTeam(dataArray[2], Integer.valueOf(dataArray[7]), Integer.valueOf(dataArray[9]));
        } else {
            System.out.println("Wrong dataline found : " + Arrays.toString(dataArray));
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGivenGoal() {
        return givenGoal;
    }

    public void setGivenGoal(int givenGoal) {
        this.givenGoal = givenGoal;
    }

    public int getReceivedGoal() {
        return receivedGoal;
    }

    public void setReceivedGoal(int receivedGoal) {
        this.receivedGoal = receivedGoal;
    }

    public int getGoalDiff() {
        return Math.abs(this.givenGoal - this.receivedGoal);
    }

    @Override
    public String toString() {
        return "FootbalTeam{" +
                "name='" + name + '\'' +
                ", givenGoal=" + givenGoal +
                ", receivedGoal=" + receivedGoal +
                '}';
    }

}