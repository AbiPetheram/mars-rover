package input.parsing;

import input.Instruction;

public class InstructionParser {
    public Instruction[] parseInstructions(String input){
        if(input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Instruction[] result = new Instruction[input.length()];
        result[0] = Instruction.L;
        return result;
    }
}
