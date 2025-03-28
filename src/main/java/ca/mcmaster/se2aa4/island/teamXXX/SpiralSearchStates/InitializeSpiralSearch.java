package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;
import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class InitializeSpiralSearch implements SpiralState {

    @Override
    public String execute(RescueDrone d) {
        //Initializes tracker variables for the spiral search algorithm
        d.setStateTracker(0);
        d.setSpiralSearchIncrement(2);
        d.setCurrentStep(2);
        d.setCounter(0);
        d.setSide(1);
        d.setStateBool(false);
        return null;
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
