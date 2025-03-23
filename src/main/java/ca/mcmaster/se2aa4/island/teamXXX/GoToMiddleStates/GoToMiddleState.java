package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public interface GoToMiddleState { 

    abstract String execute(Drone d);
    abstract GoToMiddleState nextState();
}

