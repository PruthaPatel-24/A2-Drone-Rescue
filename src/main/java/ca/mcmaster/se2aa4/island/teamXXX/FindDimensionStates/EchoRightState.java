package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Right;

public class EchoRightState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        return d.echo(Right);
    }

    @Override
    public FindDimensionState nextState() {
        return new CompareState();
    }

}
