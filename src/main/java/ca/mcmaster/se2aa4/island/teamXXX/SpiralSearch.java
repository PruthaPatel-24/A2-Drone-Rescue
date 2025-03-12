package ca.mcmaster.se2aa4.island.teamXXX;

public class SpiralSearch extends Explorer{
    int current_step = 1;
    int increment = 1;

    /*
     * 1. go to middle (need x and y coordinates from explorer)
     * 2. forward -> turn -> forward -> turn -> increase step size by increment (I think we need to scan each step)
     * 3. check battery level between changing step size
     * 3. if a full loop scans ocean, end spiral search (we're outside of island)
     * 4. for now if emergency site, and one creek found, end search (we can try for closest creek later)
     * 
     * **Need to 
     */
}