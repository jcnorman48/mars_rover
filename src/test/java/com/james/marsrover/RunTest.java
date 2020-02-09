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

    //this test would test the parser as well, no validation check on it though
    //as there's no external input

}
