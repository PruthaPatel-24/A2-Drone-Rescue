package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class EchoLeftState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.echo(Left);
    }

    public FindDimensionState nextState(){
        return new EchoRightState();
    }

}

