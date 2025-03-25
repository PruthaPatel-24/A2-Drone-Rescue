package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class EchoAheadAdjustmentState implements SpiralState {

    @Override
    public String execute(RescueDrone d) {
        d.setStateBool(false);
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
