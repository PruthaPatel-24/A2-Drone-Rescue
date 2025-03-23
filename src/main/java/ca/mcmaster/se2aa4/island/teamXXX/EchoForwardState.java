package ca.mcmaster.se2aa4.island.teamXXX;

import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

public class EchoForwardState implements FindDimensionState {


    FindDimensionState nextState = new StartState();
    public String execute(Drone d){
        ///nextState = d.forwardRangeDecision(); -- old
        return d.echo(Forward);
    }

    public FindDimensionState nextState(){
        //return nextState; --old 
        return new IncrementForwardState();
        //IF echo forward terrain NOT oor 
        //dimension of axis i am facing ++  
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

