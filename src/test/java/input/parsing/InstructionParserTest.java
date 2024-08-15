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
    void testReturnsArrayWithL(){
        InstructionParser ip = new InstructionParser();
        Instruction[] result = ip.parseInstructions("L");
        assertArrayEquals(new Instruction[]{Instruction.L}, result);
    }

    @Test
    void testReturnsArrayWithFiveLs(){
        InstructionParser ip = new InstructionParser();
        Instruction[] result = ip.parseInstructions("LLLLL");
        Instruction[] expected = new Instruction[]{Instruction.L, Instruction.L, Instruction.L, Instruction.L, Instruction.L};
        assertArrayEquals(expected, result);
    }

    @Test
    void testReturnsArrayWithLRMIndividually(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertArrayEquals(new Instruction[]{Instruction.L}, ip.parseInstructions("L")),
                () -> assertArrayEquals(new Instruction[]{Instruction.R}, ip.parseInstructions("R")),
                () -> assertArrayEquals(new Instruction[]{Instruction.M}, ip.parseInstructions("M"))
                );
    }

    @Test
    void testReturnsArrayWithMixedStringValidInput(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertArrayEquals(new Instruction[]{Instruction.L, Instruction.R, Instruction.M}, ip.parseInstructions("LRM")),
                () -> assertArrayEquals(new Instruction[]{Instruction.R, Instruction.R, Instruction.R, Instruction.M}, ip.parseInstructions("RRRM")),
                () -> assertArrayEquals(new Instruction[]{Instruction.M, Instruction.R, Instruction.L, Instruction.M}, ip.parseInstructions("MRLM"))
        );
    }

    @Test
    void testReturnsArrayWithMixedStringInvalidInput(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        ()-> ip.parseInstructions("LSM")),
                () -> assertThrows(IllegalArgumentException.class,
                        ()-> ip.parseInstructions("PRRM")),
                () -> assertThrows(IllegalArgumentException.class,
                        ()->  ip.parseInstructions("MR9M"))
        );
    }

    @Test
    void testReturnsArrayWhenPassedLowerCase(){
        InstructionParser ip = new InstructionParser();
        assertAll(
                () -> assertArrayEquals(new Instruction[]{Instruction.L, Instruction.R, Instruction.M}, ip.parseInstructions("lrm")),
                () -> assertArrayEquals(new Instruction[]{Instruction.R, Instruction.R, Instruction.R, Instruction.M}, ip.parseInstructions("rrrm")),
                () -> assertArrayEquals(new Instruction[]{Instruction.M, Instruction.R, Instruction.L, Instruction.M}, ip.parseInstructions("mrlm"))
        );
    }

}