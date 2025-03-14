package ca.mcmaster.se2aa4.island.teamXXX;

interface DroneActions {
    String fly();
    String turnLeft();
    String turnRight();
    String scan();
    String stop();
    String echo(Compass heading);
}
