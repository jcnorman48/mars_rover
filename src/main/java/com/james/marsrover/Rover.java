package com.james.marsrover;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Creates instance of Rover.
 * This will create a rover initialized with a Grid Size and a Starting <code>Position</code>
 */
public final class Rover {

    private final Position startingPosition;

    //mapping of an Orientation to the next point for a Move
    static final Function<Position,Position> moves = p -> {
        switch(p.getOrientation()){
            case N: return new Position(p.getX(), p.getY()+1, p.getOrientation());
            case E: return new Position(p.getX()+1, p.getY(), p.getOrientation());
            case S: return new Position(p.getX(), p.getY()-1, p.getOrientation());
            case W: return new Position(p.getX()-1, p.getY(), p.getOrientation());
            default: throw new IllegalArgumentException("Unknown Orientation: " + p.getOrientation());
        }
    };

    //mapping of a Position to next Position based on Operation
    static final BiFunction<Position,Operation,Position> operations = (p, o) -> {
        switch(o){
            case M: return moves.apply(p);
            case L: return new Position(p.getX(), p.getY(), p.getOrientation().getLeft());
            case R: return new Position(p.getX(), p.getY(), p.getOrientation().getRight());
            default: throw new IllegalArgumentException("Unknown Operation: " + o);
        }
    };

    final Function<Position,Position> validate;

    public Rover(int gridWidth, int gridHeight, Position startingPosition){
        this.startingPosition = startingPosition;

        validate = p -> {
            if( p.getY() > gridHeight || p.getY() < 0 || p.getX() > gridWidth || p.getX() < 0){
                throw new IllegalArgumentException("Cannot move rover outside the grid to: x:"
                        + p.getX() + ", y:" + p.getY() );
            }
            return p;
        };
    }

    /**
     * Execute the List of Operations on this rover.
     * @param operations Operations to execute. Will throw IllegalArgumentException if the
     *                   rover goes off the grid.
     * @return final position. Position after all the operations are applied
     */
    public Position executeOperations(List<Operation> operations){

        return operations.stream()
                .reduce(startingPosition, Rover.operations.andThen(validate),
                (currentPosition, nextPosition) -> nextPosition);
    }
}
