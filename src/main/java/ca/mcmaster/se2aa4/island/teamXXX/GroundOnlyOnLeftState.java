package ca.mcmaster.se2aa4.island.teamXXX;

public class GroundOnlyOnLeftState implements FindDimensionState {
    
    public String execute(Drone d){
        d.updateRunningDimensionEchoRight();
        //adding two increments here to account for the two spaces skipped when turning 
        d.incrementRunningDimension();
        d.incrementRunningDimension();
        return d.turnLeft();
    }

    public FindDimensionState nextState(){
        return new IncrementForwardState();
    }

}

