package ca.mcmaster.se2aa4.island.teamXXX;

public class GroundOnlyOnRightState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        //add echo right to the axis that i am NOT facing 
        return d.turnRight();
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new IncrementForwardState();
    }

}

