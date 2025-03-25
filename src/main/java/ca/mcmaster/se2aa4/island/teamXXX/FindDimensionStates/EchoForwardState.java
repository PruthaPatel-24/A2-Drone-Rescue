package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class EchoForwardState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        return d.echo(Forward);
    }

    @Override
    public FindDimensionState nextState() {
        return new IncrementForwardState();
    }

}
