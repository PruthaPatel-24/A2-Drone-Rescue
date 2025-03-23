package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public interface FindDimensionState { 

    abstract String execute(Drone d);
    abstract FindDimensionState nextState();
}

