package input.parsing;

import input.CompassDirection;
import input.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateParserTest {

    @Test
    void testNullCoordinateThrowsIllegalArgumentException(){
        CoordinateParser cp = new CoordinateParser();
        assertThrows(IllegalArgumentException.class,
                ()-> cp.parseCoordinates(null)
        );
    }

    @Test
    void testArrayOfOneCoordinateThrowsIllegalArgumentException(){
        CoordinateParser cp = new CoordinateParser();
        assertThrows(IllegalArgumentException.class,
                ()-> cp.parseCoordinates(new String[]{"1"})
        );
    }

    @Test
    void testAlphabeticalCharacterThrowsIllegalArgumentException(){
        CoordinateParser cp = new CoordinateParser();
        assertThrows(IllegalArgumentException.class,
                ()-> cp.parseCoordinates(new String[]{"1", "A"})
        );
    }

    @Test
    void testThreeNumbersInputThrowsIllegalArgumentException(){
        CoordinateParser cp = new CoordinateParser();
        assertThrows(IllegalArgumentException.class,
                ()-> cp.parseCoordinates(new String[]{"1","2", "2"})
        );
    }

    @Test
    void testCoordinatesReturnedWhenTwoNumbersInput(){
        CoordinateParser cp = new CoordinateParser();
        Coordinates result = cp.parseCoordinates(new String[] {"1","2"});
        assertEquals(new Coordinates(1, 2), result);
    }

    @Test
    void testCoordinatesReturnedWhenTwoDoubleDigitNumbersInput(){
        CoordinateParser cp = new CoordinateParser();
        Coordinates result = cp.parseCoordinates(new String[] {"12","26"});
        assertEquals(new Coordinates(12, 26), result);
    }

}