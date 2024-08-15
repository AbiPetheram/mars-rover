package input.parsing;

import input.CompassDirection;
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
                ()-> cp.parseCoordinates(new char[]{1})
        );
    }

    @Test
    void testAlphabeticalCharacterThrowsIllegalArgumentException(){
        CoordinateParser cp = new CoordinateParser();
        assertThrows(IllegalArgumentException.class,
                ()-> cp.parseCoordinates(new char[]{1, 'A'})
        );
    }

    @Test
    void testHashMapReturnedWhenTwoNumbersInput(){
        CoordinateParser cp = new CoordinateParser();
        HashMap<Character, Integer> result = cp.parseCoordinates(new char[] {'1','2'});
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('x', 1);
        expected.put('y', 2);
        assertEquals(expected, result);
    }

}