package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class StartSpiralState implements SpiralState {

    @Override
    public String execute(Drone d) {
        return d.scan();
    }

    @Override
    public SpiralState nextState() {
        return null;
    }
}
