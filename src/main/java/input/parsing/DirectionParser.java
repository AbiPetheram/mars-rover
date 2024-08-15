package input.parsing;

import input.CompassDirection;

public class DirectionParser {

    public CompassDirection parseDirection(String input){
        if(input.length() > 1 || !Character.isAlphabetic(input.charAt(0))){
            throw new IllegalArgumentException();
        }
        return switch (input) {
            case "N" -> CompassDirection.NORTH;
            case "E" -> CompassDirection.EAST;
            case "S"-> CompassDirection.SOUTH;
            case "W" -> CompassDirection.WEST;
            default -> throw new IllegalArgumentException();
        };
    }
}
