package ca.mcmaster.se2aa4.island.teamXXX;

public class GoToEdgeState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.fly();
    }

    public FindDimensionState nextState(){
        return new LookForEdgeState();
    }

}

