package ca.mcmaster.se2aa4.island.teamXXX;

public class Battery {
    int battery_life;

    public Battery(int battery) {
        this.battery_life = battery;
    }

    public boolean reduce_battery(int cost) {
        battery_life = battery_life - cost;

        if (battery_life < 200) {
            return true;
        }
        return false;
    }

    public int getBatteryLife() {
        return battery_life;
    }
}
