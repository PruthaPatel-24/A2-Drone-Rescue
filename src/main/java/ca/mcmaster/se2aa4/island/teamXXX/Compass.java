package ca.mcmaster.se2aa4.island.teamXXX;

public enum Compass {
    N, E, S, W;
    
    // Turn right
    public String next() {
        return values()[(this.ordinal() + 1) % values().length].name();
    }

    // Turn left
    public String previous() {
        return values()[(this.ordinal() - 1 + values().length) % values().length].name();
    }
}
