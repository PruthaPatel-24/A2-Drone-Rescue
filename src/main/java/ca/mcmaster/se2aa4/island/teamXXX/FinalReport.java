package ca.mcmaster.se2aa4.island.teamXXX;

public class FinalReport {

    IslandMap map;

    public FinalReport(IslandMap m) {
        this.map = m;
    }

    public String finalReport() {;
        return map.closestCreek();
    }
}
