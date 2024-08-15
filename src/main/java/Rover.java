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

    }
}
