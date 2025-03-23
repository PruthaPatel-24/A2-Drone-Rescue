package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class StartGTMState implements GoToMiddleState {

    public String execute(Drone d){
        return d.scan();
    }

    public GoToMiddleState nextState(){
        return new FixPositionState();
    }
}

