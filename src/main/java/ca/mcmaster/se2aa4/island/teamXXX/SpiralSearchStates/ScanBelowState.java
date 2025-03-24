package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class ScanBelowState implements SpiralState {

    @Override
    public String execute(RescueDrone d) {
        return d.scan();
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
