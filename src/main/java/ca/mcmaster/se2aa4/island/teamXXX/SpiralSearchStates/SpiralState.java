package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public interface SpiralState { 

    abstract String execute(RescueDrone d);
    abstract SpiralState nextState();
}

