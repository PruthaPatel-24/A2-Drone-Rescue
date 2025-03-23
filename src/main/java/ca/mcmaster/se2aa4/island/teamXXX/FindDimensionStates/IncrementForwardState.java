package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class IncrementForwardState implements FindDimensionState {
    
    FindDimensionState nextState = new StartFDState();
    public String execute(Drone d){
        if (d.forwardRangeDecision()){
            d.incrementRunningDimension(); 
            nextState = new EchoForwardState();
            return d.fly();
        }   
        else{
            nextState = new IncrementForwardDoneState();
            return d.scan();

        }
    }

    public FindDimensionState nextState(){
        //return new EchoForwardState();
        return nextState;
    }

}

