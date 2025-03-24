package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.IslandMap;

public class FinalSpiralSearchState implements SpiralState {

    SpiralState nextState = new StartSpiralState();
    IslandMap map = IslandMap.getInstance();

    @Override
    public String execute(Drone d) {
        if (map.foundBoth() == true) {
            nextState = null;
        }
        nextState = new StartSpiralState();
        return d.scan();
    }

    @Override
    public SpiralState nextState() {
        return nextState;
    }
}
