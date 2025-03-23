package ca.mcmaster.se2aa4.island.teamXXX;

import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

public class LookForEdgeState implements FindDimensionState {
    
    //FindDimensionState nextState = new StartState();
    public String execute(Drone d){
        //nextState = d.edgeFoundDecision();
        return d.echo(Forward);
    }

    public FindDimensionState nextState(){
        //return nextState;
        return new GoToEdgeState();

    }

}

