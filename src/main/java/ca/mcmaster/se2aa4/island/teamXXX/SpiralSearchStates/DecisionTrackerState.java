package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class DecisionTrackerState implements SpiralState {

    SpiralState nextState;
    int cur;

    @Override
    public String execute(Drone d) {
        d.setStateTracker(d.getStateTracker() + 1);
        d.setCounter(d.getCounter() + 1);

        if (d.getSide() == 3) {
            cur = d.getCurrentStep();
            cur = cur + d.getSpiralSearchIncrement();
            d.setCurrentStep(cur);
            d.setStateBool(false);
            d.setSide(1);
        }

        if (d.getCounter() > d.getCurrentStep()) {
            d.setStateBool(true);
        }

        if (d.getStateBool() == true) {
            nextState = new EchoAheadAdjustmentState();
        } else if (d.getCounter() <= d.getCurrentStep() && d.getStateBool() == false) {
            nextState = new ScanBelowState();
        } else {
            nextState = d.decideState();
        }
        return d.scan();
    }

    @Override
    public SpiralState nextState() {
        return nextState;
    }
}
