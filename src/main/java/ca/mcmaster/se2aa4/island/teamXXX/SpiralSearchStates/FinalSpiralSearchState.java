package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.IslandMap;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;
import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class FinalSpiralSearchState implements SpiralState {

    SpiralState nextState = new DecisionTrackerState();
    IslandMap map = IslandMap.getInstance();

    @Override
    public String execute(RescueDrone d) {
        //If emergency site and at least one creek is found, end the spiral search
        if (map.foundBoth() == true) {
            nextState = null;
        } else {
            nextState = new DecisionTrackerState();
        }
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return nextState;
    }
}
