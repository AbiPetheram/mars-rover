import input.CompassDirection;
import input.Coordinates;
import input.Instruction;
import input.Position;

public class Rover {
    private Position position;
    private Plateau plateau;

    public Rover(Position position, Plateau plateau) {
        this.position = position;
        this.plateau = plateau;
    }

    public Position getPosition() {
        return position;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void move(Instruction[] instructions){
        for (Instruction instruction: instructions){
            if(instruction == Instruction.L || instruction == Instruction.R){
                rotate(instruction);
            } else if (instruction == Instruction.M){
                moveForward(instruction);
            }
        }
    }

    private void moveForward(Instruction instruction){
        //takes Instruction.M and moves forward in a direction
        //check that the rover can move - is within plateau && position is empty
        if(instruction == Instruction.M){
            position = new Position(new Coordinates(position.getCoordinates().x(), position.getCoordinates().y() +1), position.getFacing());
        }
    }

    private void rotate(Instruction instruction){
        if(instruction == Instruction.R){
            switch(position.getFacing()){
                case NORTH -> position = new Position(position.getCoordinates(), CompassDirection.EAST);
                case EAST -> position = new Position(position.getCoordinates(), CompassDirection.SOUTH);
                case SOUTH -> position = new Position(position.getCoordinates(), CompassDirection.WEST);
                case WEST -> position = new Position(position.getCoordinates(), CompassDirection.NORTH);
            }
        } else if(instruction == Instruction.L){
            switch(position.getFacing()){
                case NORTH -> position = new Position(position.getCoordinates(), CompassDirection.WEST);
                case EAST -> position = new Position(position.getCoordinates(), CompassDirection.NORTH);
                case SOUTH -> position = new Position(position.getCoordinates(), CompassDirection.EAST);
                case WEST -> position = new Position(position.getCoordinates(), CompassDirection.SOUTH);
            }
        }
    }
}
