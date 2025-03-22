package ca.mcmaster.se2aa4.island.teamXXX;

public class FinalDimensionFindState implements FindDimensionState {
    
    public String execute(Drone d){
        return d.stop(); 
    }

    public FindDimensionState nextState(){
        return new StartState();
    }

}

