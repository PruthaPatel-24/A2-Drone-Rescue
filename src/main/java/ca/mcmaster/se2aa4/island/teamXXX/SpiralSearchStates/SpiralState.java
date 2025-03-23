package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public interface SpiralState { 

    abstract String execute(Drone d);
    abstract SpiralState nextState();
}

