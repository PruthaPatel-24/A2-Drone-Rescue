package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class InitializeSpiralSearch implements SpiralState {

    @Override
    public String execute(Drone d) {
        d.setStateTracker(0);
        d.setSpiralSearchIncrement(2);
        d.setCurrentStep(2);
        d.setCounter(0);
        d.setSide(1);
        d.setStateBool(false);
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
