package com.james.marsrover;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

    @Test
    public void testGetters(){
        Position p = new Position(1,2,Orientation.N);
        assertSame(p.getX(), 1);
        assertSame(p.getY(), 2);
        assertSame(p.getOrientation(), Orientation.N);
    }
}
