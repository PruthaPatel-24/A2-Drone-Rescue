package ca.mcmaster.se2aa4.island.teamXXX;

public enum Compass {
    N, E, S, W;
    
    // Turn right
    public Compass next() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    // Turn left
    public Compass previous() {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }

}
