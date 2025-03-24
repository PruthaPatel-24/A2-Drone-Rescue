package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class LookForEdgeState implements FindDimensionState {

    //FindDimensionState nextState = new StartState();
    @Override
    public String execute(RescueDrone d) {
        //nextState = d.edgeFoundDecision();
        return d.echo(Forward);
    }

    @Override
    public FindDimensionState nextState() {
        //return nextState;
        return new GoToEdgeState();

    }

}
