package ca.mcmaster.se2aa4.island.teamXXX;

import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

public class UTurnPartTwoState implements FindDimensionState {
    
    public String execute(Drone d){
        d.updateRunningDimensionEchoForward();
        return d.turnLeft();
    }

    public FindDimensionState nextState(){
        return new IncrementForwardState();

    }

}

