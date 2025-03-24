package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class InitializeSpiralSearch implements SpiralState {

    @Override
    public String execute(Drone d) {
        d.setStateTracker(-1);
        d.setSpiralSearchIncrement(2);
        d.setCurrentStep(2);
        d.setCounter(0);
        d.setSide(1);
        d.setStateBool(false);
        return d.scan();
    }

    @Override
    public SpiralState nextState() {
        return new StartSpiralState();
    }
}
