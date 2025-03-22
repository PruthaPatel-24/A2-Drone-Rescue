package ca.mcmaster.se2aa4.island.teamXXX;

public class EchoForwardState implements FindDimensionState {
    
    public String execute(Drone d, Compass heading){
        //add echo right to the axis that i am NOT facing 
        return d.echo(heading);
    }

    public FindDimensionState nextState(int dimensionsFound){
        //IF echo forward terrain NOT oor 
        //dimension of axis i am facing ++  
        return new IncrementForwardState();
        /*else
            dimension of axis i am facing += echoForwardRange
            dimensionsFound ++ (pass by reference)
            if dimensionsFound <2
                return startState
            else 
                return FinalDimensionFindState
        */

    }

}

