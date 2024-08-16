package logic;
import input.Coordinates;
import input.Position;

import java.util.ArrayList;

public class MissionControl {
    ArrayList<Rover> rovers;

    public MissionControl() {
        this.rovers = new ArrayList<>();
    }

    public Plateau createPlateau(Coordinates coordinates){
        if(coordinates == null){
            throw new IllegalArgumentException();
        }
        return new Plateau(coordinates);
    }

    public Rover createRover(Plateau plateau, Position position){
        Rover rover = new Rover(position, plateau);
        rovers.add(rover);
        return rover;
    }
}
