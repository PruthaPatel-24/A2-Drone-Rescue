package ca.mcmaster.se2aa4.island.teamXXX;

public class Execution {
    private Drone drone;
    SpiralSearch search = new SpiralSearch();

    public Execution(Drone drone) {
        this.drone = drone;
    }

    public void executeDrone() {
        drone.goToMiddle();
        search.spiralSearchAlgorithm(drone);
    }
}
