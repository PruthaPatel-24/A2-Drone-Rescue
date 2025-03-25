package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public interface SpiralState { //Interface for spiral search actions and transitions to the next state

    abstract String execute(RescueDrone d);
    abstract SpiralState nextState();
}
