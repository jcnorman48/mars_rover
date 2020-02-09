package com.james.marsrover;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RoverTest {

    @Test
    public void testExecuteOperations(){
        Position start = new Position(0,0,Orientation.N);
        Rover r = new Rover(2,2, start);
        List<Operation> ops = new ArrayList<>();
        ops.add(Operation.R);
        Position nextPos = r.executeOperations(ops);
        assertSame(nextPos.getX(), start.getX());
        assertSame(nextPos.getY(), start.getY());
        assertSame(nextPos.getOrientation(), Orientation.E);
    }

    @Test
    public void testOps(){
        //ensure all enums are covered
        for(Operation op:Operation.values()){
            assertNotNull(Rover.operations.apply(new Position(1,1,Orientation.N), op));
        }
    }

    @Test
    public void testMoves(){
        for(Orientation o:Orientation.values()){
            assertNotNull(Rover.moves.apply(new Position(1,1, o)));
        }
    }
}
