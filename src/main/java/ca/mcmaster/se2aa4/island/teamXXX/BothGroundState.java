package ca.mcmaster.se2aa4.island.teamXXX;

public class BothGroundState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        return d.turnLeft(); // just pick a random direction 
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new StartState();
    }

}

