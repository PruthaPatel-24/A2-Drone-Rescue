package ca.mcmaster.se2aa4.island.teamXXX;

public class EchoRightState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        return d.echo(heading.next());
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new CompareState();
    }

}

