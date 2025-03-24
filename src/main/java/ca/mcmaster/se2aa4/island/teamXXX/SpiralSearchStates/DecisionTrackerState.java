package ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import static ca.mcmaster.se2aa4.island.teamXXX.Movement.Forward;

public class DecisionTrackerState implements SpiralState {

    SpiralState nextState;
    private final Logger logger = LogManager.getLogger();

    @Override
    public String execute(Drone d) {
        d.setStateTracker(d.getStateTracker() + 1);
        logger.info("state tracker value is: {}", d.getStateTracker());
        d.setCounter(d.getCounter() + 1);
        logger.info("counter value is: {}", d.getCounter());
        logger.info("current step value is: {}", d.getCurrentStep());
        logger.info("boolean value is: {}", d.getStateBool());
        logger.info("side value is: {}", d.getSide());

        if (d.getSide() == 3) {
            d.setCurrentStep(d.getCurrentStep() + d.getSpiralSearchIncrement());
            logger.info("new current step value is: {}", d.getCurrentStep());
            d.setStateBool(false);
            logger.info("new boolean value is: {}", d.getStateBool());
            d.setSide(1);
            logger.info("new side value is: {}", d.getSide());
        }

        if (d.getStateBool() == true) {
            d.setStateTracker(4);
            logger.info("new state tracker value is: {}", d.getStateTracker());
        }

        if (d.getCounter() <= d.getCurrentStep() && d.getStateBool() == false && d.getStateTracker() != 3) {
            d.setStateTracker(2);
            logger.info("new state tracker value is: {}", d.getStateTracker());
        } else if (d.getCounter() > d.getCurrentStep()) {
            d.setStateBool(true);
            logger.info("new boolean value is: {}", d.getStateBool());
        }
        nextState = d.decideState();
        return d.echo(Forward);
    }

    @Override
    public SpiralState nextState() {
        return nextState;
    }

}
