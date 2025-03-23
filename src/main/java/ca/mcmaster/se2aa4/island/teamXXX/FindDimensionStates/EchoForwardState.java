package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class EchoForwardState implements FindDimensionState {

    @Override
    public String execute(Drone d) {
        return d.echo(Forward);
    }

    @Override
    public FindDimensionState nextState() {
        return new IncrementForwardState();
    }

}
