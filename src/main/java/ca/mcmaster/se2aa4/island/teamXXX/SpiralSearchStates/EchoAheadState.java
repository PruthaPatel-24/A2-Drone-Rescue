package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class EchoAheadState implements SpiralState {

    @Override
    public String execute(Drone d) {
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return new DecisionTrackerState();
    }
}
