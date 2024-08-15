package input.parsing;

import input.CompassDirection;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateParserTest {

    @Test
    void testNullCoordinate(){
        CoordinateParser cp = new CoordinateParser();
        HashMap<Character, Integer> result = cp.parseCoordinates(null);
        assertEquals(null, result);
    }

}