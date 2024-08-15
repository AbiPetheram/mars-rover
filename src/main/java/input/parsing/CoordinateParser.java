package input.parsing;

import java.util.HashMap;

public class CoordinateParser {
    public HashMap<Character, Integer> parseCoordinates(String[] input) {
        HashMap<Character, Integer> result = new HashMap<>();
        if(input == null || input.length != 2){
            throw new IllegalArgumentException();
        } else if (!Character.isDigit(input[0].charAt(0)) || !Character.isDigit(input[1].charAt(0))){
            throw new IllegalArgumentException();
        } else {
            result.put('x', Integer.parseInt(input[0]));
            result.put('y', Integer.parseInt(input[1]));
        }
        return result;
    }
}
