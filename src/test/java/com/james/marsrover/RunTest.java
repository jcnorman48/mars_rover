package com.james.marsrover;

import static org.junit.Assert.*;
import org.junit.Test;

public class RunTest {

    @Test
    public void testRun(){
        String input = "5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM";
        String results = Run.run(input);

        assertEquals(results, "1 3 N 5 1 E");
    }

    @Test
    public void testValidParser(){
        String input = "5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM";
        assertSame(Run.Parser.parse(input).gridWidth, 5);
    }

    @Test(expected = Run.ParserException.class)
    public void testInvalidParser(){
        Run.Parser.parse("bad input");
    }

}
