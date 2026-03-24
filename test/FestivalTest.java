import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FestivalTest {
    Festival festival;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
       //ARRANGE
       festival = new Festival();
        ArrayList<String> stagesdata = FileIO.readData("data/stages.csv");
        festival.createStages(stagesdata);

    }

    @org.junit.jupiter.api.Test
    void calcTotalCapcity() {
        //ACT
         int actual = festival.calcTotalCapcity();
         int expected = 7800;

        //ASSERT
        assertEquals(actual,expected);

    }
}