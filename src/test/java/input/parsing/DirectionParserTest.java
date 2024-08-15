package input.parsing;

import input.CompassDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionParserTest {
    @Test
    void testNullDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection(null);
        assertEquals(null, result);
    }

    @Test
    void testInvalidDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection(new String[]{"q"});
        assertEquals(null, result);
    }
}