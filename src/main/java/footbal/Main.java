package footbal;

public class Main {

    public static void main(String[] args) {
        Footbal footbal = new Footbal();
        String fileName = "datamunging/football.dat";
        FootbalTeam footbalTeam = footbal.getWinnerTeam(fileName);
        System.out.println("Result team: " + footbalTeam);
    }

}