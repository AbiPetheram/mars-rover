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
                else if(!missionControl.isPositionEmpty(moveForward(instruction).getCoordinates(), plateau)){
                    throw new IllegalArgumentException();
                }
                position = moveForward(instruction);
            }
        }
        return position;
    }

    private Position moveForward(Instruction instruction){
        if(instruction == Instruction.M){
            return switch(position.getFacing()){
                case N ->
                    new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() +1), position.getFacing());
                case E ->
                    new Position(new Coordinates(position.getCoordinates().x() + 1, position.getCoordinates().y()), position.getFacing());
                case S ->
                    new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() - 1), position.getFacing());
                case W ->
                    new Position(new Coordinates(position.getCoordinates().x() - 1, position.getCoordinates().y()), position.getFacing());
            };
        }
        return position;
    }

    private Position rotate(Instruction instruction){
        if(instruction == Instruction.R){
            return switch (position.getFacing()) {
                case N -> new Position(position.getCoordinates(), CompassDirection.E);
                case E -> new Position(position.getCoordinates(), CompassDirection.S);
                case S -> new Position(position.getCoordinates(), CompassDirection.W);
                case W -> new Position(position.getCoordinates(), CompassDirection.N);
            };
        } else if(instruction == Instruction.L){
            return switch(position.getFacing()){
                case N -> new Position(position.getCoordinates(), CompassDirection.W);
                case E -> new Position(position.getCoordinates(), CompassDirection.N);
                case S -> new Position(position.getCoordinates(), CompassDirection.E);
                case W -> new Position(position.getCoordinates(), CompassDirection.S);
            };
        }
        return position;
    }
}
