package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class StartGTMState implements GoToMiddleState {

    @Override
    public String execute(RescueDrone d) {
        return null;
    }

    @Override
    public GoToMiddleState nextState() {
        return new FixPositionState();
    }
}
