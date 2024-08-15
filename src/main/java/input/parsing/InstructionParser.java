package input.parsing;

import input.Instruction;

public class InstructionParser {
    public Instruction[] parseInstructions(String input){
        if(input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Instruction[] result = new Instruction[input.length()];
        for (int i = 0; i < input.length(); i++){
            switch(input.toUpperCase().charAt(i)){
                case 'L' -> result[i] = Instruction.L;
                case 'R' -> result[i] = Instruction.R;
                case 'M' -> result[i] = Instruction.M;
            }
        }
        return result;
    }
}
