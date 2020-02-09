package com.james.marsrover;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrientationTest {

    @Test
    public void testLeft(){
        assertSame(Orientation.N.getLeft(), Orientation.W);
        assertSame(Orientation.E.getLeft(), Orientation.N);
        assertSame(Orientation.S.getLeft(), Orientation.E);
        assertSame(Orientation.W.getLeft(), Orientation.S);
    }

    @Test
    public void testRight(){
        assertSame(Orientation.N.getRight(), Orientation.E);
        assertSame(Orientation.E.getRight(), Orientation.S);
        assertSame(Orientation.S.getRight(), Orientation.W);
        assertSame(Orientation.W.getRight(), Orientation.N);
    }
}
