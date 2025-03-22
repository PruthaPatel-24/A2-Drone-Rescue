package ca.mcmaster.se2aa4.island.teamXXX;

public class BothGroundState implements FindDimensionState {
    
    public String execute(Drone d){
        d.incrementRunningDimension();
        d.incrementRunningDimension();
        return d.turnLeft(); // just pick a random direction 
    }

    public FindDimensionState nextState(){
        return new GoToEdgeState();
    }

}

