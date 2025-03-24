package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class CompareState implements FindDimensionState {

    FindDimensionState compareResults;

    @Override
    public String execute(Drone d) {
        this.compareResults = d.compareEchoData();
        return d.scan();
    }

    @Override
    public FindDimensionState nextState() {
        return compareResults;

    }

}
