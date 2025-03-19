package ca.mcmaster.se2aa4.island.teamXXX;

public class FinalReport {
    IslandMap map;
    public FinalReport(IslandMap m) {
        this.map = m;
    }
    public String finalReport() {
        int[] closest_creek = map.closestCreek();

        return "closest creek found to emergency site is at x: " + closest_creek[0] + " y: " + closest_creek[1];
    }   
}
