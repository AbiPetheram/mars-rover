package input.parsing;

import input.Coordinates;

import java.util.HashMap;

public class CoordinateParser {
    public Coordinates parseCoordinates(String[] input) {
        if(input == null || input.length != 2){
            throw new IllegalArgumentException();
        } else if (!Character.isDigit(input[0].charAt(0)) || !Character.isDigit(input[1].charAt(0))){
            throw new IllegalArgumentException();
        } else {
            return new Coordinates(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
    }
}
