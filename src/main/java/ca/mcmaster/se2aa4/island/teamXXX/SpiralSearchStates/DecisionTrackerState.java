package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class DecisionTrackerState implements SpiralState {

    SpiralState nextState;

    @Override
    public String execute(RescueDrone d) {
        d.setStateTracker(d.getStateTracker() + 1);
        d.setCounter(d.getCounter() + 1);

        if (d.getSide() == 3) {
            d.setCurrentStep(d.getCurrentStep() + d.getSpiralSearchIncrement());
            d.setStateBool(false);
            d.setSide(1);
        }

        if (d.getStateBool() == true) {
            d.setStateTracker(4);
        }

        if (d.getCounter() <= d.getCurrentStep() && d.getStateBool() == false && d.getStateTracker() != 3) {
            d.setStateTracker(2);
        } else if (d.getCounter() > d.getCurrentStep()) {
            d.setStateBool(true);
        }
        nextState = d.decideState();
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return nextState;
    }

}
