package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public interface GoToMiddleState { 

    abstract String execute(RescueDrone d);
    abstract GoToMiddleState nextState();
}

