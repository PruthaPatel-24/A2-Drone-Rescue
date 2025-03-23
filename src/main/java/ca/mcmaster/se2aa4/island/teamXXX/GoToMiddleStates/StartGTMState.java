package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.Compass;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.*;

public class StartGTMState implements GoToMiddleState {
    
    GoToMiddleState nextState = new FinalGTMState();

    public String execute(Drone d){
        Compass h = d.getHeading();
        int middleX = d.getMaxX()/2;
        int middleY = d.getMaxY()/2;
        if (Math.abs(d.getCurrentX() - middleX) <=2  && Math.abs(d.getCurrentY() - middleY) <=2){
            nextState = new FinalGTMState();
        }
        else{
            nextState = new StartGTMState();
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

