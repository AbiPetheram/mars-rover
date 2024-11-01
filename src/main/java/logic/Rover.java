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
               rotateRight();
            } else if(instruction == Instruction.L){
                rotateLeft();
            } else if (instruction == Instruction.M){
                moveForward(instruction);
            }
        }
        return position;
    }

    private void moveForward(Instruction instruction){
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
        position = newPosition;
    }

    private void rotateRight(){
        switch (position.getFacing()) {
            case N -> position = new Position(position.getCoordinates(), CompassDirection.E);
            case E -> position = new Position(position.getCoordinates(), CompassDirection.S);
            case S -> position = new Position(position.getCoordinates(), CompassDirection.W);
            case W -> position = new Position(position.getCoordinates(), CompassDirection.N);
        };
    }

    private void rotateLeft(){
        switch(position.getFacing()){
            case N -> position = new Position(position.getCoordinates(), CompassDirection.W);
            case E -> position = new Position(position.getCoordinates(), CompassDirection.N);
            case S -> position = new Position(position.getCoordinates(), CompassDirection.E);
            case W -> position = new Position(position.getCoordinates(), CompassDirection.S);
        };
    }

}
