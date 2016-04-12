package edu.nyu.cs.cs101.consumers;

import java.util.ArrayList;

/**
 * An <code>EvadignBall</code> is a <code>Ball</code> that is always trying to
 * move in the direction away from the nearest ball to it.
 * 
 * 
 */
public class EvadingBall extends ExpandableBall {

    /**
     * Constructor with arguments
     * 
     * @param xPos location for this <code>Ball</code>
     * @param yPos location for this <code>Ball</code>
     */
    public EvadingBall(int xPos, int yPos) {
        super(xPos, yPos);
        super.setColor(0, 255, 0);
    }

    /**
     * Always accelerates away from the closest ball.
     * 
     * @param objects list of balls that it may want to try and evade
     */
    void accelerate(ArrayList<GeometricObject> objects) {
    	GeometricObject predator = getClosest(this, objects);
        double predatorX = predator.getX();
        double predatorY = predator.getY();
        this.decelerate(predatorX, predatorY); 
    }

}
