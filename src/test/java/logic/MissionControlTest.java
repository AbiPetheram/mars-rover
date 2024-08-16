package logic;

import input.Coordinates;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {

    @Test
    void createPlateauNullInput(){
        MissionControl mc = new MissionControl();
        assertThrows(IllegalArgumentException.class,
                ()-> mc.createPlateau(null)
        );
    }

    @Test
    void createPlateauValidInput(){
        MissionControl mc = new MissionControl();
        Plateau expected = new Plateau(new Coordinates(2,3));
        assertThat(mc.createPlateau(new Coordinates(2,3)), samePropertyValuesAs(expected));
    }

}