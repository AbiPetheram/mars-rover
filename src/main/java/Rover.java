import input.CompassDirection;
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

    public void rotate(Instruction instruction){
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
