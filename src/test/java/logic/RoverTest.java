package logic;

import input.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RoverTest {
    @Test
    void testNullInputMove(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), CompassDirection.E);
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
                arguments(CompassDirection.N, CompassDirection.E, Instruction.R),
                arguments(CompassDirection.E, CompassDirection.S, Instruction.R),
                arguments(CompassDirection.S, CompassDirection.W, Instruction.R),
                arguments(CompassDirection.W, CompassDirection.N, Instruction.R),
                arguments(CompassDirection.N, CompassDirection.W, Instruction.L),
                arguments(CompassDirection.W, CompassDirection.S, Instruction.L),
                arguments(CompassDirection.S, CompassDirection.E, Instruction.L),
                arguments(CompassDirection.E, CompassDirection.N, Instruction.L)
        );
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingNorth(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), CompassDirection.N);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(0,1), CompassDirection.N);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingEast(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), CompassDirection.E);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(1,0), CompassDirection.E);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingWest(){
        Position position = new Position(new Coordinates(1,1), CompassDirection.W);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        MissionControl mc = new MissionControl();
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(0,1), CompassDirection.W);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveForwardMoveWithSingleInstructionStartingSouth(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(1,1), CompassDirection.S);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{Instruction.M});
        Position expected = new Position(new Coordinates(1,0), CompassDirection.S);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @ParameterizedTest
    @MethodSource("multiRotateTestData")
    void testMoveRotateWithMultipleInstructions(CompassDirection initial, CompassDirection resulting, Instruction instruction1, Instruction instruction2, Instruction instruction3){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(0,0), initial);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{instruction1, instruction2, instruction3});
        Position expected = new Position(new Coordinates(0,0), resulting);
        assertThat(result, samePropertyValuesAs(expected));
    }

    static Stream<Arguments> multiRotateTestData(){
        return Stream.of(
                arguments(CompassDirection.N, CompassDirection.W, Instruction.R, Instruction.R, Instruction.R),
                arguments(CompassDirection.E, CompassDirection.S, Instruction.R, Instruction.L, Instruction.R),
                arguments(CompassDirection.S, CompassDirection.E, Instruction.R, Instruction.L, Instruction.L),
                arguments(CompassDirection.N, CompassDirection.E, Instruction.L, Instruction.L, Instruction.L),
                arguments(CompassDirection.W, CompassDirection.S, Instruction.L, Instruction.R, Instruction.L),
                arguments(CompassDirection.S, CompassDirection.W, Instruction.L, Instruction.R, Instruction.R)
        );
    }

    @ParameterizedTest
    @MethodSource("threeSpacesMoveForwardTestData")
    void testMoveForwardThreeSpacesAllDirections(CompassDirection direction, Instruction move, Coordinates coordinates){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(3,3), direction);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{move, move, move});
        Position expected = new Position(coordinates, direction);
        assertThat(result, samePropertyValuesAs(expected));
    }

    static Stream<Arguments> threeSpacesMoveForwardTestData(){
        return Stream.of(
                arguments(CompassDirection.N, Instruction.M, new Coordinates(3, 6)),
                arguments(CompassDirection.E, Instruction.M, new Coordinates(6, 3)),
                arguments(CompassDirection.S, Instruction.M, new Coordinates(3, 0)),
                arguments(CompassDirection.W, Instruction.M, new Coordinates(0, 3))
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
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{instruction1, instruction2, instruction3, instruction4});
        Position expected = new Position(endCoordinates, endDirection);
        assertThat(result, samePropertyValuesAs(expected));
    }

    static Stream<Arguments> moveForwardAndRotateTestData(){
        return Stream.of(
                arguments(CompassDirection.N, CompassDirection.S, new Coordinates(4, 2),
                        Instruction.R, Instruction.M, Instruction.R, Instruction.M),
                arguments(CompassDirection.E, CompassDirection.W, new Coordinates(1, 3),
                        Instruction.L, Instruction.L, Instruction.M, Instruction.M),
                arguments(CompassDirection.S, CompassDirection.W, new Coordinates(1, 2),
                        Instruction.M, Instruction.R, Instruction.M, Instruction.M),
                arguments(CompassDirection.N, CompassDirection.E, new Coordinates(4, 3),
                        Instruction.L, Instruction.L, Instruction.L, Instruction.M),
                arguments(CompassDirection.W, CompassDirection.E, new Coordinates(4, 2),
                        Instruction.L, Instruction.M, Instruction.L, Instruction.M)
        );
    }

    @Test
    void testMoveWhenEndCoordinates00Plateau(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(1,1), CompassDirection.S);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        Position result = rover.move(new Instruction[]{Instruction.M, Instruction.R, Instruction.M});
        Position expected = new Position(new Coordinates(0,0), CompassDirection.W);
        assertThat(result, samePropertyValuesAs(expected));
    }

    @Test
    void testMoveWhenCoordinatesEndBiggerThanPlateau(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(1,1), CompassDirection.N);
        Plateau plateau = new Plateau(new Coordinates(3, 3));
        Rover rover = mc.createRover(position, plateau);
        assertThrows(IllegalArgumentException.class,
                ()-> rover.move(new Instruction[]{Instruction.M, Instruction.M, Instruction.M, Instruction.M})
        );
    }

    @Test
    void testMoveWhenEndNegativeCoordinates(){
        MissionControl mc = new MissionControl();
        Position position = new Position(new Coordinates(1,1), CompassDirection.S);
        Plateau plateau = new Plateau(new Coordinates(10, 10));
        Rover rover = mc.createRover(position, plateau);
        assertThrows(IllegalArgumentException.class,
                ()-> rover.move(new Instruction[]{Instruction.M, Instruction.M, Instruction.M})
        );
    }
}