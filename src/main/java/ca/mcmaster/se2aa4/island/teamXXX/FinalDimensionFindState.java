package ca.mcmaster.se2aa4.island.teamXXX;

public class FinalDimensionFindState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.turnLeft(); // just pick a random direction 
    }

    public FindDimensionState nextState(){
        return new StartState();
    }

}

