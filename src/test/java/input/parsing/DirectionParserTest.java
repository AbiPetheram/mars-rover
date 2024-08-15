package input.parsing;

import input.CompassDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionParserTest {
    @Test
    void testNullDirection(){
        CompassDirection result = DirectionParser.parseDirection(null);
        assertEquals(null, result);
    }
}