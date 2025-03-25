package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Compass;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.E;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.N;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.S;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.W;
import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class FixPositionState implements GoToMiddleState {
    GoToMiddleState nextState = new FinalGTMState();

    @Override
    public String execute(RescueDrone d) {
        Compass h = d.getHeading();
        int middleX = d.getMaxX() / 2;
        int middleY = d.getMaxY() / 2;
        if (Math.abs(d.getCurrentX() - middleX) <= 2 && Math.abs(d.getCurrentY() - middleY) <= 2) {
            nextState = new FinalGTMState();
        } else {
            nextState = new FixPositionState();
        }
        if (h == N && middleY < d.getCurrentY()
                || h == E && middleX > d.getCurrentX()
                || h == S && middleY > d.getCurrentY()
                || h == W && middleX < d.getCurrentX()) {
            return d.fly();
        } else if (h.next() == N && middleY < d.getCurrentY()
                || h.next() == E && middleX > d.getCurrentX()
                || h.next() == S && middleY > d.getCurrentY()
                || h.next() == W && middleX < d.getCurrentX()) {
            return d.turnRight();
        } else {
            return d.turnLeft();
        }

    }

    @Override
    public GoToMiddleState nextState() {
        return nextState;
    }

}
