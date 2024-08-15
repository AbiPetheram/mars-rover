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

    @Test
    void testEastDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection('E');
        assertEquals(CompassDirection.EAST, result);
    }

    @Test
    void testSouthDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection('S');
        assertEquals(CompassDirection.SOUTH, result);
    }

    @Test
    void testWestDirection(){
        DirectionParser dp = new DirectionParser();
        CompassDirection result = dp.parseDirection('W');
        assertEquals(CompassDirection.WEST, result);
    }
}