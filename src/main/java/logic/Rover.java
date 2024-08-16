package logic;

import input.CompassDirection;
import input.Coordinates;
import input.Instruction;
import input.Position;

public class Rover {
    private Position position;
    private Plateau plateau;
    private MissionControl missionControl;

    public Rover(Position position, Plateau plateau, MissionControl missionControl) {
        this.position = position;
        this.plateau = plateau;
        this.missionControl = missionControl;
    }

    public Position getPosition() {
        return position;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Position move(Instruction[] instructions){
        if (instructions == null) {return position;};
        for (Instruction instruction: instructions){
            if(instruction == Instruction.L || instruction == Instruction.R){
                position = rotate(instruction);
            } else if (instruction == Instruction.M){
                if(!missionControl.isPositionInPlateau(moveForward(instruction).getCoordinates(), plateau)) {
                    throw new IllegalArgumentException();
                }
                position = moveForward(instruction);
            }
        }
        return position;
    }

    private Position moveForward(Instruction instruction){
        //takes Instruction.M and moves forward in a direction
        //check that the rover can move - is within plateau && position is empty
        if(instruction == Instruction.M){
            return switch(position.getFacing()){
                case NORTH ->
                    new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() +1), position.getFacing());
                case EAST ->
                    new Position(new Coordinates(position.getCoordinates().x() + 1, position.getCoordinates().y()), position.getFacing());
                case SOUTH ->
                    new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() - 1), position.getFacing());
                case WEST ->
                    new Position(new Coordinates(position.getCoordinates().x() - 1, position.getCoordinates().y()), position.getFacing());
            };
        }
        return position;
    }

    private Position rotate(Instruction instruction){
        if(instruction == Instruction.R){
            return switch (position.getFacing()) {
                case NORTH -> new Position(position.getCoordinates(), CompassDirection.EAST);
                case EAST -> new Position(position.getCoordinates(), CompassDirection.SOUTH);
                case SOUTH -> new Position(position.getCoordinates(), CompassDirection.WEST);
                case WEST -> new Position(position.getCoordinates(), CompassDirection.NORTH);
            };
        } else if(instruction == Instruction.L){
            return switch(position.getFacing()){
                case NORTH -> new Position(position.getCoordinates(), CompassDirection.WEST);
                case EAST -> new Position(position.getCoordinates(), CompassDirection.NORTH);
                case SOUTH -> new Position(position.getCoordinates(), CompassDirection.EAST);
                case WEST -> new Position(position.getCoordinates(), CompassDirection.SOUTH);
            };
        }
        return position;
    }
}
