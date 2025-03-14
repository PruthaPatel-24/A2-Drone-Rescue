package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpiralSearch {
    private final Logger logger = LogManager.getLogger();
    int current_step = 2;
    int increment = 2;
    int state = -1;
    int i = 0;
    int increase_step = 0;
    boolean currentStateReached = false;

    Navigator n = new Navigator();
    Compass current_heading = n.getC();
    Drone d = new Drone(current_heading);

    //forward -> turn -> forward -> turn -> increase step size by increment
    public String spiralSearchAlgorithm() {
        state++;
        i++;

        if (increase_step % 2 == 0) {
            logger.info("increasing step size");
            current_step = current_step + increment;
            currentStateReached = false;
            i = 0;
        }

        if (i < current_step && currentStateReached == false && state != 2) {
            state = 1;
        }
        else{
            currentStateReached = true;
        }

        if (state == 0) {
            logger.info("in state 0");
            return d.echo(current_heading);
        }
        else if (state == 1) {
            logger.info("in state 1");
            return d.fly();
        }
        else if (state == 2) {
            logger.info("in state 2");
            return d.scan();
        }
        else if (state == 3) {
            logger.info("in state 3");
            current_heading = current_heading.previous();
            return d.echo(current_heading);
        }
        else if (state == 4) {
            state = -1;
            logger.info("in state 4");
            increase_step++;
            logger.info(increase_step);
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