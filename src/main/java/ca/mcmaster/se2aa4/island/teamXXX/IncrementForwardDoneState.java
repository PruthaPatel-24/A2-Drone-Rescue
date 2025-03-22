package ca.mcmaster.se2aa4.island.teamXXX;

public class IncrementForwardDoneState implements FindDimensionState {
    FindDimensionState nextState = new StartState();
    public String execute(Drone d){
        d.updateRunningDimensionEchoForward();
        d.updateDimension(true); 
        
        if (d.bothDimensionsFound()){
            nextState = new FinalDimensionFindState(); //first state of middle machine! 
        }
        else{
            nextState = new StartState();
        }
        return d.scan();
    }

    public FindDimensionState nextState(){
        return nextState;
    }

}

