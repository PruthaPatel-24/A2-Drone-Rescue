package ca.mcmaster.se2aa4.island.teamXXX;

public class IncrementForwardState implements FindDimensionState {
    
    public String execute(Drone d){
        d.incrementRunningDimension(); 
        return d.fly();
    }

    public FindDimensionState nextState(){
        return new EchoForwardState();
    }

}

