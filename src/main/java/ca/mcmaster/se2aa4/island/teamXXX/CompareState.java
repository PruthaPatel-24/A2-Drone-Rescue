package ca.mcmaster.se2aa4.island.teamXXX;

public class CompareState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        return d.scan(); // actually start spiralling somehow 
    }

    public FindDimensionState nextState(int dimensionsFound){
        /*
        if echo left and echo right are both ground
            return BothOutOfRangestate
        else if both OOR
            return BothGroundState
        else if left = oor
            return TurnLeftState
        else if right == oor
            return TurnLeftState
        */
        return new StartState (); //just a place holder till other state classes are implemented
            
    }

}

