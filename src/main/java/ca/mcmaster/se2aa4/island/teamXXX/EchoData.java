package ca.mcmaster.se2aa4.island.teamXXX;

public class EchoData{
    Terrain landDetected;
    int range; 
        
    public Terrain getLandDetected(){
        return landDetected;
    }

    public int getRange(){
        return range;
    }
    public void setRange(int range){
       this.range = range;
    }
    
    public void setLandDetected(Terrain landDetected){
        this.landDetected = landDetected;
    }
        
}
