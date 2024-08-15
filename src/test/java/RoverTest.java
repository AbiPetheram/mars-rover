import input.CompassDirection;
import input.Coordinates;
import input.Instruction;
import input.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    @Test
    void testNullInputRotate(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(null);
        assertEquals(position, rover.getPosition());
    }

    @Test
    void testRotateInputM(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.M);
        assertEquals(position, rover.getPosition());
    }

    @Test
    void testRotateRightStartingEast(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.R);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.SOUTH);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateRightStartingSouth(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.SOUTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.R);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.WEST);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateRightStartingWest(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.WEST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.R);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateRightStartingNorth(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.R);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.EAST);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateLeftStartingEast(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.L);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateLeftStartingSouth(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.SOUTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.L);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.EAST);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateLeftStartingWest(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.WEST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.L);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.SOUTH);
        assertEquals(expected, rover.getPosition());
    }

    @Test
    void testRotateLeftStartingNorth(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.rotate(Instruction.L);
        Position expected = new Position(new Coordinates(0,0), CompassDirection.WEST);
        assertEquals(expected, rover.getPosition());
    }
}