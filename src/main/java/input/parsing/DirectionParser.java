package input.parsing;

import input.CompassDirection;

public class DirectionParser {

    public CompassDirection parseDirection(char input){
        if(!Character.isAlphabetic(input)){
            return null;
        }
        return switch (input) {
            case 'N' -> CompassDirection.NORTH;
            case 'E' -> CompassDirection.EAST;
            case 'S' -> CompassDirection.SOUTH;
            case 'W' -> CompassDirection.WEST;
            default -> null;
        };
    }
}
