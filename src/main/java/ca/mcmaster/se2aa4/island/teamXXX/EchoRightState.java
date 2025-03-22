package ca.mcmaster.se2aa4.island.teamXXX;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

public class EchoRightState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.echo(Right);
    }

    public FindDimensionState nextState(){
        return new CompareState();
    }

}

