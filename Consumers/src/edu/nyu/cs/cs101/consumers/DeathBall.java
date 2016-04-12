package edu.nyu.cs.cs101.consumers;

import java.util.ArrayList;

/**
 * A <code>DeathBall</code> is a <code>Ball</code> that is always trying to
 * move in the direction away from the nearest ball to it. 
 * 
 * Any <code>Ball</code> it collides with is instantly deleted.
 * 
 * All Death balls should be chocolate by default
 * 
 */
public class DeathBall extends ExpandableBall {

    /**
     * Constructor with arguments
     * 
     * @param xPos location for this <code>Ball</code>
     * @param yPos location for this <code>Ball</code>
     */
    public DeathBall(int xPos, int yPos) {
        super(xPos, yPos);
        super.setColor(123, 63, 0);// gives the death ball the color chocolate.
    }

    /**
     * Always accelerates towards from the closest ball.
     * 
     * @param objects list of balls that it may want to try and evade
     */
    void accelerate(ArrayList<GeometricObject> objects) {
    	GeometricObject predator = getClosest(this, objects);
        double predatorX = -predator.getX();
        double predatorY = -predator.getY();
        this.accelerate(predatorX, predatorY); //accel
    }

}
