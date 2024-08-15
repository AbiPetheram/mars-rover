package input.parsing;

import input.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    @Test
    void testNullInstructions(){
        InstructionParser ip = new InstructionParser();
        assertThrows(IllegalArgumentException.class,
                ()-> ip.parseInstructions(null)
        );
    }

}