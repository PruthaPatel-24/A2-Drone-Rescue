package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class StartSpiralSearch implements SpiralState {

    @Override
    public String execute(RescueDrone d) {
        return null;
    }

    @Override
    public SpiralState nextState() {
        return new InitializeSpiralSearch();
    }
}
