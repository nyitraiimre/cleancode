package footbal;

public class FootbalTeam {

    private String name;
    private int givenGoal;
    private int receivedGoal;

    public FootbalTeam(String name, int givenGoal, int receivedGoal) {
        this.name = name;
        this.givenGoal = givenGoal;
        this.receivedGoal = receivedGoal;
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
        return Math.abs(this.givenGoal / this.receivedGoal);
    }
}