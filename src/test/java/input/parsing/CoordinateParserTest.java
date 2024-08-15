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
    void testHashMapReturnedWhenTwoNumbersInput(){
        CoordinateParser cp = new CoordinateParser();
        HashMap<Character, Integer> result = cp.parseCoordinates(new String[] {"1","2"});
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('x', 1);
        expected.put('y', 2);
        assertEquals(expected, result);
    }

    @Test
    void testHashMapReturnedWhenTwoDoubleDigitNumbersInput(){
        CoordinateParser cp = new CoordinateParser();
        HashMap<Character, Integer> result = cp.parseCoordinates(new String[] {"12","26"});
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('x', 12);
        expected.put('y', 26);
        assertEquals(expected, result);
    }

}