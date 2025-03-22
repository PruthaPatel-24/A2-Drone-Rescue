package ca.mcmaster.se2aa4.island.teamXXX;

public class GroundOnlyOnRightState implements FindDimensionState {
    
    public String execute(Drone d){
        d.updateRunningDimensionEchoLeft();
        //adding two increments here to account for the two spaces skipped when turning 
        d.incrementRunningDimension();
        d.incrementRunningDimension();
        return d.turnRight();
    }

    public FindDimensionState nextState(){
        return new IncrementForwardState();
    }

}

