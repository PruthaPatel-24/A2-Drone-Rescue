package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import static ca.mcmaster.se2aa4.island.teamXXX.Compass.E;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.N;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.S;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.W;
import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.BothGroundState;
import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.BothOutOfRangeState;
import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.FindDimensionState;
import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.GroundOnlyOnLeftState;
import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.GroundOnlyOnRightState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.EchoAheadAdjustmentState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.EchoAheadState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.FinalSpiralSearchState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.FlyForwardState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.LeftTurningState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.ScanBelowState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.SpiralState;
import static ca.mcmaster.se2aa4.island.teamXXX.Terrain.GROUND;
import static ca.mcmaster.se2aa4.island.teamXXX.Terrain.OUT_OF_RANGE;

public class RescueDrone extends Drone implements CanTurnRight, CanTurnLeft, CanEcho {

    //private Compass heading;
    private final Logger logger = LogManager.getLogger();
    JSONObject echoDecision = new JSONObject();
    JSONObject headingDecision = new JSONObject();
    JSONObject parameters = new JSONObject();
    private EchoData echoDataLeft = new EchoData();
    private EchoData echoDataRight = new EchoData();
    private EchoData echoDataForward = new EchoData();
    private Compass lastEcho;

    @Override
    public String turnLeft() {
        n.move(Movement.Left);
        //heading = heading.previous();
        headingDecision.put("action", "heading");
        parameters.put("direction", n.getC().name());
        headingDecision.put("parameters", parameters);
        return headingDecision.toString();
    }

    @Override
    public String turnRight() {
        n.move(Movement.Right);
        //heading = heading.next();
        headingDecision.put("action", "heading");
        parameters.put("direction", n.getC().name());
        headingDecision.put("parameters", parameters);
        return headingDecision.toString();
    }

    @Override
    public String echo(Movement m) {
        Compass headingToEcho = n.getC().movementToCompass(m);
        lastEcho = headingToEcho;
        logger.info("echo gave back: " + headingToEcho);
        echoDecision.put("action", "echo");
        parameters.put("direction", headingToEcho.name());
        echoDecision.put("parameters", parameters);
        return echoDecision.toString();
    }

    public void updateRunningDimensionEchoLeft() {
        n.updateRunningDimension(echoDataLeft.range);
        logger.info("running dimension is " + n.getRunningDimension());
    }

    public void updateRunningDimensionEchoRight() {
        n.updateRunningDimension(echoDataRight.range);
        logger.info("running dimension is " + n.getRunningDimension());
    }

    public void updateRunningDimensionEchoForward() {
        n.updateRunningDimension(echoDataForward.range);
        logger.info("running dimension is " + n.getRunningDimension());
    }

    public void incrementRunningDimension() {
        n.updateRunningDimension(1);
        logger.info("running dimension is " + n.getRunningDimension());
    }

    public void updateDimension(boolean mainAxis) {
        if (mainAxis) {
            n.setMainAxis();
            if (n.getC() == N) {
                n.setY(echoDataForward.range + 1);
            } else if (n.getC() == E) {
                n.setX(n.getMaxX() - echoDataForward.range);
            } else if (n.getC() == S) {
                n.setY(n.getMaxY() - echoDataForward.range);
            } else if (n.getC() == W) {
                n.setX(echoDataForward.range + 1);
            }
        } else {
            if (n.getC() == N) {
                n.setX(echoDataLeft.range + 1);
            } else if (n.getC() == E) {
                n.setY(echoDataLeft.range + 1);
            } else if (n.getC() == S) {
                n.setX(echoDataRight.range + 1);
            } else if (n.getC() == W) {
                n.setY(echoDataRight.range + 1);
            }

            n.setPerpendicularAxis();
        }
        refreshEchoData();

        n.incrementDimensionsFound();
    }

    public void refreshEchoData() {
        echoDataForward.setLandDetected(null);
        echoDataLeft.setLandDetected(null);
        echoDataLeft.setLandDetected(null);
    }

    public boolean bothDimensionsFound() {
        return (n.getDimensionsFound() == 2);
    }
    boolean inCorrectCol = false;
    boolean inCorrectRow = false;

    public void updateEchoData(int range, Terrain landDetected) {
        Movement wing = n.getC().compassToMovement(lastEcho);
        logger.info("upating echo data");
        if (wing == Movement.Left) {
            logger.info("left wing");
            echoDataLeft.setRange(range);
            echoDataLeft.setLandDetected(landDetected);
        } else if (wing == Movement.Right) {
            logger.info("right wing");
            echoDataRight.setRange(range);
            echoDataRight.setLandDetected(landDetected);
        } else if (wing == Movement.Forward) {
            echoDataForward.setRange(range);
            echoDataForward.setLandDetected(landDetected);
        }
        logger.info("acknowedged results");
    }

    public FindDimensionState compareEchoData() {
        if (echoDataLeft.getLandDetected() == GROUND && echoDataRight.getLandDetected() == GROUND) {
            return new BothGroundState();
        } else if (echoDataLeft.getLandDetected() == OUT_OF_RANGE && echoDataRight.getLandDetected() == OUT_OF_RANGE) {
            return new BothOutOfRangeState();
        } else if (echoDataLeft.getLandDetected() == GROUND && echoDataRight.getLandDetected() == OUT_OF_RANGE) {
            return new GroundOnlyOnLeftState();
        } else if (echoDataLeft.getLandDetected() == OUT_OF_RANGE && echoDataRight.getLandDetected() == GROUND) {
            return new GroundOnlyOnRightState();
        } else {
            return null;
        }
    }

    public boolean forwardRangeDecision() {
        if (echoDataForward.landDetected == OUT_OF_RANGE) {
            return false;
        } else {
            return true;
        }
    }

    public boolean edgeFoundDecision() {
        if (echoDataForward.landDetected == OUT_OF_RANGE) {
            return false;
        } else {
            return true;
        }
    }

    public Compass getHeading() {
        return n.getC();
    }

    public int getMaxX() {
        return n.getMaxX();
    }

    public int getMaxY() {
        return n.getMaxY();
    }

    public int getCurrentY() {
        return n.getCurrentY();
    }

    public int getCurrentX() {
        return n.getCurrentX();
    }

    private boolean stateBoolean;

    public boolean setStateBool(boolean bool) {
        stateBoolean = bool;
        return stateBoolean;
    }

    public boolean getStateBool() {
        return stateBoolean;
    }

    private int increment;
    private int counter;
    private int cur_step;
    private int side;
    private int stateTracker;

    public int getSpiralSearchIncrement() {
        return increment;
    }

    public int setSpiralSearchIncrement(int num) {
        increment = num;
        return increment;
    }

    public int getSide() {
        return side;
    }

    public int setSide(int num) {
        side = num;
        return side;
    }

    public int getCurrentStep() {
        return cur_step;
    }

    public int setCurrentStep(int num) {
        cur_step = num;
        return cur_step;
    }

    public int getCounter() {
        return counter;
    }

    public int setCounter(int num) {
        counter = num;
        return counter;
    }

    public int getStateTracker() {
        return stateTracker;
    }

    public int setStateTracker(int num) {
        stateTracker = num;
        return stateTracker;
    }

    public SpiralState decideState() {
        if (getStateTracker() == 1) {
            return new EchoAheadState();
        } else if (getStateTracker() == 2) {
            return new ScanBelowState();
        } else if (getStateTracker() == 3) {
            return new FlyForwardState();
        } else if (getStateTracker() == 4) {
            return new EchoAheadAdjustmentState();
        } else if (getStateTracker() == 5) {
            return new LeftTurningState();
        } else {
            return new FinalSpiralSearchState();
        }
    }

}
