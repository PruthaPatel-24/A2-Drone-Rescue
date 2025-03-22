package ca.mcmaster.se2aa4.island.teamXXX;

public class GroundOnlyOnLeftState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        //add echo right to the axis that i am NOT facing 
        return d.turnLeft();
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new IncrementForwardState();
    }

}

