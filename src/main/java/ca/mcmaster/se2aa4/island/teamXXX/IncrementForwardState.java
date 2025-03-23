package ca.mcmaster.se2aa4.island.teamXXX;

public class IncrementForwardState implements FindDimensionState {
    
    FindDimensionState nextState = new StartState();
    public String execute(Drone d){
        if (d.forwardRangeDecision()){
            d.incrementRunningDimension(); 
            nextState = new EchoForwardState();
            return d.fly();
        }   
        else{
            nextState = new IncrementForwardDoneState();
            return d.scan();

        }
    }

    public FindDimensionState nextState(){
        //return new EchoForwardState();
        return nextState;
    }

}

