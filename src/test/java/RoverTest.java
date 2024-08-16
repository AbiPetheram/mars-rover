import input.*;
import logic.MissionControl;
import logic.Plateau;
import logic.Rover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RoverTest {
    @Test
    void testNullInputMove(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(null);
        assertThat(result, samePropertyValuesAs(position));
    }

    @ParameterizedTest
    @MethodSource("rotateTestData")
    void testMoveRotateWithSingleInstruction(CompassDirection initial, CompassDirection resulting, Instruction instruction){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), initial);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{instruction});
        Position expected = new Position(new Coordinates(0,0), resulting);
        assertThat(result, samePropertyValuesAs(expected));
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
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), CompassDirection.NORTH);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(0,1), CompassDirection.NORTH);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingEast(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), CompassDirection.EAST);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(1,0), CompassDirection.EAST);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingWest(){
        Position position = new Position(new Coordinates(1,1), CompassDirection.WEST);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        MissionControl mc = new MissionControl();
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(0,1), CompassDirection.WEST);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingSouth(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(1,1), CompassDirection.SOUTH);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(1,0), CompassDirection.SOUTH);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @ParameterizedTest
    @MethodSource("multiRotateTestData")
    void testMoveRotateWithMultipleInstructions(CompassDirection initial, CompassDirection resulting, Instruction instruction1, Instruction instruction2, Instruction instruction3){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), initial);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{instruction1, instruction2, instruction3});
        Position expected = new Position(new Coordinates(0,0), resulting);
        assertThat(result, samePropertyValuesAs(expected));
    }

    static Stream<Arguments> multiRotateTestData(){
        return Stream.of(
                arguments(CompassDirection.NORTH, CompassDirection.WEST, Instruction.R, Instruction.R, Instruction.R),
                arguments(CompassDirection.EAST, CompassDirection.SOUTH, Instruction.R, Instruction.L, Instruction.R),
                arguments(CompassDirection.SOUTH, CompassDirection.EAST, Instruction.R, Instruction.L, Instruction.L),
                arguments(CompassDirection.NORTH, CompassDirection.EAST, Instruction.L, Instruction.L, Instruction.L),
                arguments(CompassDirection.WEST, CompassDirection.SOUTH, Instruction.L, Instruction.R, Instruction.L),
                arguments(CompassDirection.SOUTH, CompassDirection.WEST, Instruction.L, Instruction.R, Instruction.R)
        );
    }

    @ParameterizedTest
    @MethodSource("threeSpacesMoveForwardTestData")
    void testMoveForwardThreeSpacesAllDirections(CompassDirection direction, Instruction move, Coordinates coordinates){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(3,3), direction);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{move, move, move});
        Position expected = new Position(coordinates, direction);
        assertThat(result, samePropertyValuesAs(expected));
    }

    static Stream<Arguments> threeSpacesMoveForwardTestData(){
        return Stream.of(
                arguments(CompassDirection.NORTH, Instruction.M, new Coordinates(3, 6)),
                arguments(CompassDirection.EAST, Instruction.M, new Coordinates(6, 3)),
                arguments(CompassDirection.SOUTH, Instruction.M, new Coordinates(3, 0)),
                arguments(CompassDirection.WEST, Instruction.M, new Coordinates(0, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("moveForwardAndRotateTestData")
    void testMoveForwardAndRotate(CompassDirection initial, CompassDirection endDirection, Coordinates endCoordinates,
                                  Instruction instruction1, Instruction instruction2,
                                  Instruction instruction3, Instruction instruction4){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(3,3), initial);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = new Rover(position, plateau, mc);
        Position result = rover.move(new Instruction[]{instruction1, instruction2, instruction3, instruction4});
        Position expected = new Position(endCoordinates, endDirection);
        assertThat(result, samePropertyValuesAs(expected));
    }

    static Stream<Arguments> moveForwardAndRotateTestData(){
        return Stream.of(
                arguments(CompassDirection.NORTH, CompassDirection.SOUTH, new Coordinates(4, 2),
                        Instruction.R, Instruction.M, Instruction.R, Instruction.M),
                arguments(CompassDirection.EAST, CompassDirection.WEST, new Coordinates(1, 3),
                        Instruction.L, Instruction.L, Instruction.M, Instruction.M),
                arguments(CompassDirection.SOUTH, CompassDirection.WEST, new Coordinates(1, 2),
                        Instruction.M, Instruction.R, Instruction.M, Instruction.M),
                arguments(CompassDirection.NORTH, CompassDirection.EAST, new Coordinates(4, 3),
                        Instruction.L, Instruction.L, Instruction.L, Instruction.M),
                arguments(CompassDirection.WEST, CompassDirection.EAST, new Coordinates(4, 2),
                        Instruction.L, Instruction.M, Instruction.L, Instruction.M)
        );
    }
}