package logic;
import input.Coordinates;
import input.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class MissionControl {
    HashMap<Plateau, ArrayList<Rover>> plateauRovers;

    public MissionControl() {
        this.plateauRovers = new HashMap<>();
    }

    public Plateau createPlateau(Coordinates coordinates){
        if(coordinates == null){
            throw new IllegalArgumentException();
        }
        Plateau plateau = new Plateau(coordinates);
        plateauRovers.put(plateau, new ArrayList<>());
        return plateau;
    }

    public Rover createRover(Position position, Plateau plateau){
        if(position == null || plateau == null){
            throw new IllegalArgumentException();
        }
        if(!plateauRovers.containsKey(plateau)){
            plateauRovers.put(plateau, new ArrayList<Rover>());
        }
        if(!isPositionEmpty(position.getCoordinates(), plateau)){
            throw new IllegalArgumentException();
        }
        Rover rover = new Rover(position, plateau, this);
        plateauRovers.get(plateau).add(rover);
        return rover;
    }

    public boolean isPositionInPlateau(Coordinates coordinates, Plateau plateau){
        if(coordinates.x() <= plateau.getSize().x() && coordinates.x() >= 0){
            if(coordinates.y() <= plateau.getSize().y() && coordinates.y() >= 0){
                return true;
            }
        }
        return false;
    }

    public boolean isPositionEmpty(Coordinates coordinates, Plateau plateau, Rover movingRover){
        for(Rover rover : plateauRovers.get(plateau)){
            if(rover.equals(movingRover)){
                continue;
            }
            if(rover.getPosition().getCoordinates().equals(coordinates)){
                return false;
            }
        }
        return true;
    }


}
