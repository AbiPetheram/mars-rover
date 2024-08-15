package input.parsing;

import input.CompassDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionParserTest {
    @Test
    void testEmptyCharDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection(' ');
        assertEquals(null, result);
    }

    @Test
    void testNumberDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection('1');
        assertEquals(null, result);
    }

    @Test
    void testSpecialCharacterDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection('%');
        assertEquals(null, result);
    }

    @Test
    void testNorthDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection('N');
        assertEquals(CompassDirection.NORTH, result);
    }
}