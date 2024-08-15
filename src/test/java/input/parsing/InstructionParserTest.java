package input.parsing;

import input.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    @Test
    void testNullInstructionsThrowsIllegalArgumentException(){
        InstructionParser ip = new InstructionParser();
        assertThrows(IllegalArgumentException.class,
                ()-> ip.parseInstructions(null)
        );
    }

    @Test
    void testEmptyStringInstructionsThrowsIllegalArgumentException(){
        InstructionParser ip = new InstructionParser();
        assertThrows(IllegalArgumentException.class,
                ()-> ip.parseInstructions("")
        );
    }

    @Test
    void testNumericalInstructionsThrowsIllegalArgumentException(){
        InstructionParser ip = new InstructionParser();
        assertThrows(IllegalArgumentException.class,
                ()-> ip.parseInstructions("23")
        );
    }

    @Test
    void testLReturnsArrayWithL(){
        InstructionParser ip = new InstructionParser();
        Instruction[] result = ip.parseInstructions("L");
        assertArrayEquals(new Instruction[]{Instruction.L}, result);
    }

    @Test
    void testLReturnsArrayWithFiveLs(){
        InstructionParser ip = new InstructionParser();
        Instruction[] result = ip.parseInstructions("LLLLL");
        Instruction[] expected = new Instruction[]{Instruction.L, Instruction.L, Instruction.L, Instruction.L, Instruction.L};
        assertArrayEquals(expected, result);
    }

}