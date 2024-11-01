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
            if(instruction == Instruction.R){
                position = rotateRight();
            } else if(instruction == Instruction.L){
                position = rotateLeft();
            } else if (instruction == Instruction.M){
                position = moveForward(instruction);
            }
        }
        return position;
    }

    private Position moveForward(Instruction instruction){
        Position newPosition = new Position(new Coordinates(0,0), CompassDirection.N);
        switch(position.getFacing()){
            case N ->
                newPosition = new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() +1), position.getFacing());
            case E ->
                newPosition = new Position(new Coordinates(position.getCoordinates().x() + 1, position.getCoordinates().y()), position.getFacing());
            case S ->
                newPosition = new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() - 1), position.getFacing());
            case W ->
                newPosition = new Position(new Coordinates(position.getCoordinates().x() - 1, position.getCoordinates().y()), position.getFacing());
        };
        if(!missionControl.isPositionInPlateau(newPosition.getCoordinates(), plateau) || !missionControl.isPositionEmpty(newPosition.getCoordinates(), plateau)){
            throw new IllegalArgumentException();
        }
        return newPosition;
    }

    private Position rotateRight(){
        return switch (position.getFacing()) {
            case N -> new Position(position.getCoordinates(), CompassDirection.E);
            case E -> new Position(position.getCoordinates(), CompassDirection.S);
            case S -> new Position(position.getCoordinates(), CompassDirection.W);
            case W -> new Position(position.getCoordinates(), CompassDirection.N);
        };
    }

    private Position rotateLeft(){
        return switch(position.getFacing()){
            case N -> new Position(position.getCoordinates(), CompassDirection.W);
            case E -> new Position(position.getCoordinates(), CompassDirection.N);
            case S -> new Position(position.getCoordinates(), CompassDirection.E);
            case W -> new Position(position.getCoordinates(), CompassDirection.S);
        };
    }

}
