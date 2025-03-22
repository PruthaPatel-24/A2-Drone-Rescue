package ca.mcmaster.se2aa4.island.teamXXX;

public class IncrementForwardState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        //add echo right to the axis that i am NOT facing 
        return d.fly();
    }

    public FindDimensionState nextState(int dimensionsFound){
        return new EchoForwardState();
    }

}

