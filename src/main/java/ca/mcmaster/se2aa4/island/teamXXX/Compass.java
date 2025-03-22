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

    public Movement compassToMovement(Compass direction){
        if (this.ordinal() == direction.ordinal()){
            return Movement.Forward;
        }
        else if (this.previous().ordinal() == direction.ordinal()){
            return Movement.Left;
        }
        else if (this.next().ordinal() == direction.ordinal()){
            return Movement.Right;
        }
        else{
            return Movement.Backward; 
        }
    }

}
