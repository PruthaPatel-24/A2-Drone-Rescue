package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class CompareState implements FindDimensionState {
    
    FindDimensionState compareResults; 
    public String execute(Drone d){ 
        this.compareResults = d.compareEchoData();
        return d.scan();
    }

    public FindDimensionState nextState(){
        return compareResults;
        
        /*
        if echo left and echo right are both ground
            return BothOutOfRangestate
        else if both OOR
            return BothGroundState
        else if left = oor
            return TurnRightState
        else if right == oor
            return TurnLeftState
        */
            
    }

}

