package input.parsing;

import input.CompassDirection;

public class DirectionParser {

    public CompassDirection parseDirection(char[] input){
        for (char value : input){
            if(!Character.isAlphabetic(value)){
                return null;
            }
        }
        return null;
    }
}
