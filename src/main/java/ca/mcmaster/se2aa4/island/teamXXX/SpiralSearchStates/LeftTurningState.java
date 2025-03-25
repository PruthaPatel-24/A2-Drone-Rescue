package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class LeftTurningState implements SpiralState {

    @Override
    public String execute(RescueDrone d) {
        //Helps increment the length of the drone flight path and resets tracker variables to redo spiral search
        d.setStateTracker(0);
        d.setCounter(0);
        d.setStateBool(false);
        d.setSide(d.getSide() + 1);
        return d.turnLeft();
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
