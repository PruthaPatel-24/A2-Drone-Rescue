package ca.mcmaster.se2aa4.island.teamXXX;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

public class EchoLeftState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.echo(Left);
    }

    public FindDimensionState nextState(){
        return new EchoRightState();
    }

}

