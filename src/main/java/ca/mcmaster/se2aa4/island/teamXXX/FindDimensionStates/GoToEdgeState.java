package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class GoToEdgeState implements FindDimensionState {
    
    FindDimensionState nextState = new StartFDState();
    public String execute(Drone d){
        if (d.edgeFoundDecision()){
            nextState = new LookForEdgeState();
            return d.fly();
            
        }
        else{
            nextState = new UTurnPartOneState();
            return d.scan();
        }
        
    }
    public FindDimensionState nextState(){
        //return new LookForEdgeState();
        return nextState;
    }

}

