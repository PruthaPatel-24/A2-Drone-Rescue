package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.Compass;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixPositionState implements GoToMiddleState {
    private final Logger logger = LogManager.getLogger();

    GoToMiddleState nextState = new FinalGTMState();

    public String execute(Drone d){
        logger.info("x, y: " + d.getCurrentX() + " " + d.getCurrentY());
        Compass h = d.getHeading();
        int middleX = d.getMaxX()/2;
        int middleY = d.getMaxY()/2;
        if (Math.abs(d.getCurrentX() - middleX) <=2  && Math.abs(d.getCurrentY() - middleY) <=2){
            logger.info("current x: " + d.getCurrentX() + "middle x: " + middleX);
            logger.info("current y: " + d.getCurrentY() + "middle y: " + middleY);
            nextState = new FinalGTMState();
        }
        else{
            logger.info("into here");
            nextState = new FixPositionState();
        }
        if (h == N && middleY < d.getCurrentY() ||
        h == E && middleX > d.getCurrentX() ||
        h == S && middleY > d.getCurrentY() ||
        h == W && middleX < d.getCurrentX()){
            return d.fly();
        }
        else if (h.next() == N && middleY < d.getCurrentY() ||
        h.next() == E && middleX > d.getCurrentX() ||
        h.next() == S && middleY > d.getCurrentY() ||
        h.next() == W && middleX < d.getCurrentX())
        {
            return d.turnRight();
        }
        else{
            return d.turnLeft();
        }
        
    
    }

    public GoToMiddleState nextState(){
        return nextState;
    }

}

