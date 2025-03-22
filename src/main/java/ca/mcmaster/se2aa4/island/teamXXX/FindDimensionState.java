package ca.mcmaster.se2aa4.island.teamXXX;

public interface FindDimensionState {    
    abstract String execute(Drone d, Compass heading);
    abstract FindDimensionState nextState(int dimensionsFound);
}

