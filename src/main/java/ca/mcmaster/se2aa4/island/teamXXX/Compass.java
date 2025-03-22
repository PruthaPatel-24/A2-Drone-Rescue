package ca.mcmaster.se2aa4.island.teamXXX;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.*;

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

    public Compass movementToCompass (Movement m){
        if (m == Left){
            return this.previous();
        }
        else if (m == Right){
            return this.next();
        }
        else if (m == Forward){
            return this;
        }
        else{
            return this.next().next();
        }
    }

}
