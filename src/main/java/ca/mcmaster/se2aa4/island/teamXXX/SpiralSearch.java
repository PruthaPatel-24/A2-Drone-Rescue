package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpiralSearch {

    private final Logger logger = LogManager.getLogger();
    int current_step = 4;
    int increment = 4;
    int state = -1;
    int i = 0;
    int side = 1;
    boolean currentStateReached = false;

    Navigator n = Navigator.getInstance();
    Compass current_heading;
    Drone d;

    public SpiralSearch(Drone drone) {
        this.d = drone;
        Navigator n = Navigator.getInstance();
        current_heading = n.getC();
    }

    //forward -> turn -> forward -> turn -> increase step size by increment
    public String spiralSearchAlgorithm() {
        state++;
        i++;

        if (side == 3) {
            current_step = current_step + increment;
            currentStateReached = false;
            side = 1;
        }
        if (currentStateReached == true) {
            state = 3;
        }

        if (i <= current_step && currentStateReached == false && state != 2) {
            state = 1;
        } else if (i > current_step) {
            currentStateReached = true;
        }

        if (state == 0) {
            return d.echo(current_heading);
        } else if (state == 1) {
            return d.fly();
        } else if (state == 2) {
            return d.scan();
        } else if (state == 3) {
            current_heading = current_heading.previous();
            currentStateReached = false;
            return d.echo(current_heading);
        } else if (state == 4) {
            state = -1;
            i = 0;
            currentStateReached = false;
            side++;
            return d.turnLeft();
        } else {
            logger.info("outside of states: stopping");
            return d.stop();
        }
    }
}
