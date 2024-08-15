package input.parsing;

import java.util.HashMap;

public class CoordinateParser {
    public HashMap<Character, Integer> parseCoordinates(char[] input) {
        HashMap<Character, Integer> result = new HashMap<>();
        if(input == null || input.length != 2){
            throw new IllegalArgumentException();
        } else if (!Character.isDigit(input[0]) || !Character.isDigit(input[1])){
            throw new IllegalArgumentException();
        } else {
            result.put('x', Character.getNumericValue(input[0]));
            result.put('y', Character.getNumericValue(input[1]));
        }
        return result;
    }
}
