package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Battery {
    int battery_life;
    private final Logger logger = LogManager.getLogger();

    public Battery(int battery) {
        this.battery_life = battery;
    }

    public boolean reduce_battery(int cost) {
        battery_life = battery_life - cost;
        logger.info("battery life: " + battery_life);

        if (battery_life < 200) {
            logger.info("low battery");
            return true;
        }
        return false;
    }

    public int getBatteryLife() {
        return battery_life;
    }
}
