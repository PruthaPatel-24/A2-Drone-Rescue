package ca.mcmaster.se2aa4.island.teamXXX;

public class SpiralSearch extends Explorer{
    int current_step = 1;
    int increment = 1;

    Navigator n = new Navigator();
    Compass current_heading = n.getC();
    Drone d = new Drone(current_heading);

    //forward -> turn -> forward -> turn -> increase step size by increment
    public void spiralSearchAlgorithm() {
        for (int i=0; i<current_step; i++) {
            d.echo(current_heading); //echo in front
            d.fly();
            d.scan();
        }
        current_heading = current_heading.previous();
        d.echo(current_heading);
        d.turnLeft();  
        for (int i=0; i<current_step; i++) {
            d.echo(current_heading); //echo in front
            d.fly();
            d.scan();
        }
        current_heading = current_heading.previous();
        d.echo(current_heading);
        d.turnLeft();
        current_step = current_step + increment;
    }


    /*
     * 1. go to middle (need x and y coordinates from explorer)
     * 2. forward -> turn -> forward -> turn -> increase step size by increment (I think we need to scan each step)
     * 3. check battery level between changing step size
     * 4. for now if emergency site, and one creek found, end search (we can try for closest creek later)
     * 
     * **Need to 
     */
}