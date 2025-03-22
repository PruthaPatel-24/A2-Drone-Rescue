package ca.mcmaster.se2aa4.island.teamXXX;

public class EchoLeftState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        return d.echo(heading.previous());
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new EchoRightState();
    }

}

