import input.CompassDirection;
import input.Coordinates;
import input.Instruction;
import input.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RoverTest {
    @Test
    void testNullInputMove(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.move(null);
        assertEquals(position, rover.getPosition());
    }

    @ParameterizedTest
    @MethodSource("rotateTestData")
    void testMoveRotateWithSingleInstruction(CompassDirection initial, CompassDirection resulting, Instruction instruction){
        Position position = new Position(new Coordinates(0,0), initial);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.move(new Instruction[]{instruction});
        Position expected = new Position(new Coordinates(0,0), resulting);
        assertThat(rover.getPosition(), samePropertyValuesAs(expected));
    }

    static Stream<Arguments> rotateTestData(){
        return Stream.of(
                arguments(CompassDirection.NORTH, CompassDirection.EAST, Instruction.R),
                arguments(CompassDirection.EAST, CompassDirection.SOUTH, Instruction.R),
                arguments(CompassDirection.SOUTH, CompassDirection.WEST, Instruction.R),
                arguments(CompassDirection.WEST, CompassDirection.NORTH, Instruction.R),
                arguments(CompassDirection.NORTH, CompassDirection.WEST, Instruction.L),
                arguments(CompassDirection.WEST, CompassDirection.SOUTH, Instruction.L),
                arguments(CompassDirection.SOUTH, CompassDirection.EAST, Instruction.L),
                arguments(CompassDirection.EAST, CompassDirection.NORTH, Instruction.L)
        );
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingNorth(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(0,1), CompassDirection.NORTH);
        assertThat(rover.getPosition(), samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingEast(){
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(1,0), CompassDirection.EAST);
        assertThat(rover.getPosition(), samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingWest(){
        Position position = new Position(new Coordinates(1,1), CompassDirection.WEST);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(0,1), CompassDirection.WEST);
        assertThat(rover.getPosition(), samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingSouth(){
        Position position = new Position(new Coordinates(1,1), CompassDirection.SOUTH);
        Plateau plateau = new Plateau();
        Rover rover = new Rover(position, plateau);
        rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(1,0), CompassDirection.SOUTH);
        assertThat(rover.getPosition(), samePropertyValuesAs(expected));
    }

}