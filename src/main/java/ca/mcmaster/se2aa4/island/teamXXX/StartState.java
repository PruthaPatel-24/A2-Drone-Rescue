package ca.mcmaster.se2aa4.island.teamXXX;

public class StartState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.scan();
    }

    public FindDimensionState nextState(){
        return new EchoLeftState();
    }

}

