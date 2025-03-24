package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class FlyForwardState implements SpiralState {

    @Override
    public String execute(Drone d) {
        return d.fly();
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
