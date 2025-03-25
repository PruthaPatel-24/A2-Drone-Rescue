package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class CompareState implements FindDimensionState {

    FindDimensionState compareResults;

    @Override
    public String execute(RescueDrone d) {
        this.compareResults = d.compareEchoData();
        return null;
    }

    @Override
    public FindDimensionState nextState() {
        return compareResults;

    }

}
