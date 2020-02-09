package com.james.marsrover;

/**
 * N,S,E,W Orientation for the rover
 */
public enum Orientation {

    N,
    S,
    E,
    W;

    /*
    Statically built linked orientation to easily go left and right
     */
    static {
        Orientation.N.setLeft(Orientation.W);
        Orientation.N.setRight(Orientation.E);
        Orientation.E.setLeft(Orientation.N);
        Orientation.E.setRight(Orientation.S);
        Orientation.S.setLeft(Orientation.E);
        Orientation.S.setRight(Orientation.W);
        Orientation.W.setLeft(Orientation.S);
        Orientation.W.setRight(Orientation.N);
    }

    private Orientation left;
    private Orientation right;

    /**
     * Get the Orientation to the left of <code>this</code> orientation
     * @return next counterclockwise orientation
     */
    public Orientation getLeft(){
        return left;
    }

    /**
     * Get the Orientation to the Right of <code>this</code> orientation
     * @return next clockwise orientation
     */
    public Orientation getRight(){
        return right;
    }

    private void setLeft(Orientation left){
        this.left = left;
    }

    private void setRight(Orientation right){
        this.right = right;
    }

}