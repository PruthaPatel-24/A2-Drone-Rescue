package ca.mcmaster.se2aa4.island.teamXXX;

public class FindDimension {
    FindDimensionState state = new EchoLeftState();
    Navigator n;
    Drone d;
    int dimensionsFound = 0; //rmbr to update in final states! 

    public FindDimension(Drone d, Navigator n){
        this.d = d;
        this.n = n;
    }
    public String findDimensionAlgorithm(){
        state.nextState(dimensionsFound);
        return state.execute(d, n.getC());
    }


    /*
    public boolean skipTo7 = false; 
    int dimension = 0;
    //Compass startHeading = heading;??
    FindDimensionState FDS = ECHO_LEFT;
 
    public FindDimension (){
        
    }

    public String findDimensionAlgorithm(){
        if (FDS == ECHO_LEFT){
            return d.echo(heading.previous()); //echo with left wing
        }

    }

    public boolean getSkipTo7(){
        return skipTo7;
    }
    public void setSkipTo7(boolean b){
        skipTo7 = b;
    }

    */
}

