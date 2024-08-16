package logic;

import input.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void testIsPositionInPlateauWhenCoordinatesMaxSizePlateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = plateau.isPositionInPlateau(new Coordinates(2,3));
        assertTrue(result);
    }

    @Test
    void testIsPositionInPlateauWhenCoordinates00Plateau(){
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = plateau.isPositionInPlateau(new Coordinates(0,0));
        assertTrue(result);
    }

    @Test
    void testIsPositionInPlateauWhenXCoordinateBiggerThanPlateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = plateau.isPositionInPlateau(new Coordinates(3,2));
        assertFalse(result);
    }

    @Test
    void testIsPositionInPlateauWhenYCoordinateBiggerThanPlateau(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = plateau.isPositionInPlateau(new Coordinates(2,5));
        assertFalse(result);
    }

    @Test
    void testIsPositionInPlateauWithNegativeCoordinates(){
        MissionControl mc = new MissionControl();
        Plateau plateau = new Plateau(new Coordinates(2,3));
        boolean result = plateau.isPositionInPlateau(new Coordinates(-2,5));
        boolean result2 = plateau.isPositionInPlateau(new Coordinates(2,-5));
        assertFalse(result);
        assertFalse(result2);
    }

}