package ca.mcmaster.se2aa4.island.teamXXX;

public class SpiralSearch extends Explorer{
    int current_step = 1;
    int increment = 1;
    int state = 0;
    int increase_step = 0;

    Navigator n = new Navigator();
    Compass current_heading = n.getC();
    Drone d = new Drone(current_heading);

    //forward -> turn -> forward -> turn -> increase step size by increment
    public String spiralSearchAlgorithm() {
        if (increase_step == 2) {
            current_step = current_step + increment;
        }

        if (state == 0) {
            state++;
            return d.echo(current_heading);
        }
        else if (state == 1) {
            state++;
            return d.fly();
        }
        else if (state == 2) {
            state++;
            return d.scan();
        }
        else if (state == 3) {
            state++;
            current_heading = current_heading.previous();
            return d.echo(current_heading);
        }
        else if (state == 4) {
            state++;
            increase_step++;
            return d.turnLeft();
        }
        else {
            return d.stop();
        }
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