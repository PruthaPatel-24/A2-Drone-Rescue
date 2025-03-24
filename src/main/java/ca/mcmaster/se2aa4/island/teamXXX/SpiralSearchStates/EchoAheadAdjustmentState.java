package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Compass;
import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class EchoAheadAdjustmentState implements SpiralState {

    Compass current_heading;

    @Override
    public String execute(Drone d) {
        current_heading = d.getHeading();
        current_heading = current_heading.previous();
        d.setStateBool(false);
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
