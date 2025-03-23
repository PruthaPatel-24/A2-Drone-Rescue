package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface FindDimensionState { 

    abstract String execute(Drone d);
    abstract FindDimensionState nextState();
}

