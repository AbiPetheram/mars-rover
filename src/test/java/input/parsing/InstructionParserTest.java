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

    @Test
    void testLReturnsArrayWithLRMIndividually(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertArrayEquals(new Instruction[]{Instruction.L}, ip.parseInstructions("L")),
                () -> assertArrayEquals(new Instruction[]{Instruction.R}, ip.parseInstructions("R")),
                () -> assertArrayEquals(new Instruction[]{Instruction.M}, ip.parseInstructions("M"))
                );
    }

    @Test
    void testLReturnsArrayWithMixedStringValidInput(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertArrayEquals(new Instruction[]{Instruction.L, Instruction.R, Instruction.M}, ip.parseInstructions("LRM")),
                () -> assertArrayEquals(new Instruction[]{Instruction.R, Instruction.R, Instruction.R, Instruction.M}, ip.parseInstructions("RRRM")),
                () -> assertArrayEquals(new Instruction[]{Instruction.M, Instruction.R, Instruction.L, Instruction.M}, ip.parseInstructions("MRLM"))
        );
    }

    @Test
    void testLReturnsArrayWithMixedStringInvalidInput(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertArrayEquals(new Instruction[]{Instruction.L, Instruction.R, Instruction.M}, ip.parseInstructions("LSM")),
                () -> assertArrayEquals(new Instruction[]{Instruction.R, Instruction.R, Instruction.R, Instruction.M}, ip.parseInstructions("PRRM")),
                () -> assertArrayEquals(new Instruction[]{Instruction.M, Instruction.R, Instruction.L, Instruction.M}, ip.parseInstructions("MR9M"))
        );
    }

}