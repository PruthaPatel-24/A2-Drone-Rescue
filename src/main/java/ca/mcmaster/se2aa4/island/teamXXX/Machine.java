package ca.mcmaster.se2aa4.island.teamXXX;

enum Machine {
    
    FIND_DIMENSION, GO_TO_MIDDLE, SPIRAL_SEARCH;

    public Machine next(){
        return values()[(this.ordinal()+1)%values().length];
    }
}
