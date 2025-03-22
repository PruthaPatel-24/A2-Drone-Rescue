package ca.mcmaster.se2aa4.island.teamXXX;

public class BothOutOfRangeState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        //add echo left and echo right + 1 to the axis we are NOT facing 
        return d.turnLeft();
    }

    public FindDimensionState nextState(int dimensionsFound){
        if (dimensionsFound >=2){
            return new FinalDimensionFindState();
        }
        else{
            return new EchoLeftState();
        }
    }

}

