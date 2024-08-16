package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {

    @Test
    void createPlateauNullInput(){
        MissionControl mc = new MissionControl();
        assertThrows(IllegalArgumentException.class,
                ()-> mc.createPlateau(null)
        );
    }

}