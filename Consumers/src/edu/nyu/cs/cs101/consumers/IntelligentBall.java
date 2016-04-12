package edu.nyu.cs.cs101.consumers;

import java.util.ArrayList;

/**
 * An intelligent ball is a <code>Ball</code> that:
 * <ol>
 * <li>Tries to accelerate towards smaller balls that are near it (to consume
 * them)
 * <li>Tries to accelerate away from larger balls that are near it (to evade
 * them)
 * </ol>
 * 
 * 
 */
public class IntelligentBall extends ExpandableBall {
	
	
	 /**
     * Constructor with arguments
     * 
     * @param xPos location for this <code>Ball</code>
     * @param yPos location for this <code>Ball</code>
     */
    public IntelligentBall(int xPos, int yPos) {
        super(xPos, yPos);
        super.setColor(0, 0, 255);
    }
    
    /**
     * Accelerates away from balls bigger than it.
     * Accelerates towards balls smaller than it and balls the same size as it.
     * @param objects
     */
    void accelerate(ArrayList<GeometricObject> objects) {
    	GeometricObject closest = getClosest(this, objects);
    	double otherArea = closest.getArea();
    	if (this.getArea() < otherArea) {
    		double targetX = closest.getX();
            double targetY = closest.getY();
            this.accelerate(targetX, targetY);
    	} else if (this.getArea() > otherArea) {
    		double predatorX = closest.getX();
            double predatorY = closest.getY();
            this.decelerate(predatorX, predatorY); 
    	} else if (this.getArea() == otherArea) {
    		double slowDownX = closest.getX();
            double slowDownY = closest.getY();
            this.decelerate(slowDownX, slowDownY);
    	}
    }
    
    
}
