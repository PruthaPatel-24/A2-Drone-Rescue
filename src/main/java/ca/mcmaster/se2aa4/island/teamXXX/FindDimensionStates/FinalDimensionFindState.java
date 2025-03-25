package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class FinalDimensionFindState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        return d.scan();
    }

    @Override
    public FindDimensionState nextState() {
        return null;
    }

}
