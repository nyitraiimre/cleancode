package hu.xxx.datamunging.footbal;

import hu.xxx.datamunging.dto.FootbalTeam;

public class FootbalTest {

    void testFootbalTest() {
        FootbalTeam result = new Footbal().getWinnerTeam("datamunging/hu.xxx.datamunging.footbal.dat");
     }
     //assert
}
