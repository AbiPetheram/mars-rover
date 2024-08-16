package logic;
import input.Coordinates;
import input.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MissionControl {
    HashMap<Plateau, Rover> plateauRovers;

    public MissionControl() {
        this.plateauRovers = new HashMap<>();
    }

    public Plateau createPlateau(Coordinates coordinates){
        if(coordinates == null){
            throw new IllegalArgumentException();
        }
        return new Plateau(coordinates);
    }

    public Rover createRover(Position position, Plateau plateau){
        if(position == null || plateau == null){
            throw new IllegalArgumentException();
        }
        Rover rover = new Rover(position, plateau, this);
        plateauRovers.put(plateau, rover);
        return rover;
    }

    public boolean isPositionAvailable(Coordinates coordinates, Plateau plateau){
        if(coordinates.x() <= plateau.getSize().x() && coordinates.x() >= 0){
            if(coordinates.y() <= plateau.getSize().y() && coordinates.y() >= 0){
                return true;
            }
        }
        return false;
    }
}
