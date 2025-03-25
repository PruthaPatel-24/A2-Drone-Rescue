package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public interface FindDimensionState { 

    abstract String execute(RescueDrone d);
    abstract FindDimensionState nextState();
}

