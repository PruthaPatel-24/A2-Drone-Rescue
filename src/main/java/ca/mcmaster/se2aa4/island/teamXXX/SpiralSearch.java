package ca.mcmaster.se2aa4.island.teamXXX;

public class SpiralSearch extends Explorer{
    int current_step = 1;
    int increment = 1;

    Navigator n = new Navigator ();
    Drone d = new Drone();
    Battery b = new Battery();
    JSONObject decision;
    JSONObject parameters;
    
    public SpiralSearch (JSONObject d, JSONObject p){
        decision = d;
        parameters = p;
    }
    
    // 1. go to middle (need x and y coordinates from explorer)
    n.move_to(n.getMaxX()/2, n.getMaxY()/2); //moves to center of map

    //forward -> turn -> forward -> turn -> increase step size by increment
    decision.put("action", "echo");
    parameters.put("direction", String.valueOf(n.getC()));

    while (/*echo not out of bounds*/){
        n.move(foward);
        search();
        n.turn(left);
        search();
    }

    public void search (){
        d.fly(n.getC()); // passes in direction to move (N, E, S, W)
        d.scan();
        if (d.reduceBattery(/*cost*/) == -1){
            n.goHome();
            d.flyHome();
        }
    }

    /*
     * 1. go to middle (need x and y coordinates from explorer)
     * 2. forward -> turn -> forward -> turn -> increase step size by increment (I think we need to scan each step)
     * 3. check battery level between changing step size
     * 3. if a full loop scans ocean, end spiral search (we're outside of island)
     * 4. for now if emergency site, and one creek found, end search (we can try for closest creek later)
     * 
     * **Need to 
     */
}