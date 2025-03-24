package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class LeftTurningState implements SpiralState {

    @Override
    public String execute(Drone d) {
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
