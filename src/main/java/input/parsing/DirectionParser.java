package input.parsing;

import input.CompassDirection;

public class DirectionParser {

    public CompassDirection parseDirection(String input){
        if(input.length() > 1 || !Character.isAlphabetic(input.charAt(0))){
            throw new IllegalArgumentException();
        }
        return switch (input.toUpperCase()) {
            case "N" -> CompassDirection.N;
            case "E" -> CompassDirection.E;
            case "S"-> CompassDirection.S;
            case "W" -> CompassDirection.W;
            default -> throw new IllegalArgumentException();
        };
    }
}
