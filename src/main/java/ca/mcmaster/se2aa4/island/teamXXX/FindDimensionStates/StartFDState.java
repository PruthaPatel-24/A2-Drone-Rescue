package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class StartFDState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.scan();
    }

    public FindDimensionState nextState(){
        return new EchoLeftState();
    }

}

