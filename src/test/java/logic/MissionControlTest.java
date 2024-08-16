package logic;

import input.CompassDirection;
import input.Coordinates;
import input.Position;
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

    @Test
    void createRoverNullInput(){
        MissionControl mc = new MissionControl();
        assertThrows(IllegalArgumentException.class,
                ()-> mc.createRover(null, null)
        );
    }

    @Test
    void createRoverValidInput(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(2,3), CompassDirection.NORTH);
        Plateau plateau = new Plateau(new Coordinates(2,3));
        Rover rover = new Rover(position, plateau, mc);
        assertThat(mc.createRover(position, plateau), samePropertyValuesAs(rover));
    }

    @Test
    void testIsPositionInPlateauWhenCoordinatesMaxSizePlateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = mc.isPositionInPlateau(new Coordinates(2,3), plateau);
        assertTrue(result);
    }

    @Test
    void testIsPositionInPlateauWhenCoordinates00Plateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = mc.isPositionInPlateau(new Coordinates(0,0), plateau);
        assertTrue(result);
    }

    @Test
    void testIsPositionInPlateauWhenXCoordinateBiggerThanPlateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = mc.isPositionInPlateau(new Coordinates(3,2), plateau);
        assertFalse(result);
    }

    @Test
    void testIsPositionInPlateauWhenYCoordinateBiggerThanPlateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = mc.isPositionInPlateau(new Coordinates(2,5), plateau);
        assertFalse(result);
    }

    @Test
    void testIsPositionInPlateauWithNegativeCoordinates(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = mc.isPositionInPlateau(new Coordinates(-2,5), plateau);
        boolean result2 = mc.isPositionInPlateau(new Coordinates(2,-5), plateau);
        assertFalse(result);
        assertFalse(result2);
    }

}