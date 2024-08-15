package input.parsing;

import input.Instruction;

public class InstructionParser {
    public Instruction[] parseInstructions(String input){
        if(input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Instruction[] result = new Instruction[input.length()];
        for (int i = 0; i < input.length(); i++){
            result[i] = Instruction.L;
        }
        return result;
    }
}
