package ca.mcmaster.se2aa4.island.teamXXX;

public class BothOutOfRangeState implements FindDimensionState {
    
    FindDimensionState nextState = new StartState();
    public String execute(Drone d){
        //add echo left and echo right + 1 to the axis we are NOT facing 
        
        d.updateRunningDimensionEchoLeft();
        d.updateRunningDimensionEchoRight();
        d.incrementRunningDimension();
        d.updateDimension(false);

        if (d.bothDimensionsFound()){
            nextState = new FinalDimensionFindState(); //first state of middle
        }
        else{
            nextState = new StartState();
        }
        return d.turnLeft();
    }

    public FindDimensionState nextState(){
        return nextState;
    }

}

