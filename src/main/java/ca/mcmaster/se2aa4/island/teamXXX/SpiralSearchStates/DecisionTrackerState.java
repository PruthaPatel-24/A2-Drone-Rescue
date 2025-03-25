package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;
import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class DecisionTrackerState implements SpiralState {

    SpiralState nextState;

    @Override
    public String execute(RescueDrone d) {
        d.setStateTracker(d.getStateTracker() + 1); //State tracker maintains the current state
        d.setCounter(d.getCounter() + 1); //Counter tracks the steps within the algorithm

        //Once a side of the spiral is complete, reset and increment to a larger search radius
        if (d.getSide() == 3) {
            d.setCurrentStep(d.getCurrentStep() + d.getSpiralSearchIncrement());
            d.setStateBool(false);
            d.setSide(1);
        }

        if (d.getStateBool() == true) {
            d.setStateTracker(4);
        }
        //Ensures that we haven't reached the flying state and completes the scan before further progress
        if (d.getCounter() <= d.getCurrentStep() && d.getStateBool() == false && d.getStateTracker() != 3) {
            d.setStateTracker(2);
        } else if (d.getCounter() > d.getCurrentStep()) { //Marks the stage as complete
            d.setStateBool(true);
        }
        nextState = d.decideState(); //Decides the next state based on the state tracker
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return nextState;
    }

}
