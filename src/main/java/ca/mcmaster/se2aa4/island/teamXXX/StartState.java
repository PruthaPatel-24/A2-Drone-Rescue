package ca.mcmaster.se2aa4.island.teamXXX;

public class StartState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        return d.scan();
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new EchoLeftState();
    }

}

