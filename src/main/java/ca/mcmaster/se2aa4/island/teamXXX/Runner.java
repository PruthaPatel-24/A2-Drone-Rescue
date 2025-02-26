package ca.mcmaster.se2aa4.island.teamXXX;

import static eu.ace_design.island.runner.Runner.run;

import java.io.File;

public class Runner {

    public static void main(String[] args) {
        String filename = args[0];
        try {
            run(Explorer.class) //this repeatedly calls takeDecision() until it encouters "stop" action or no battery left
                    .exploring(new File(filename))
                    .withSeed(42L)
                    .startingAt(78, 78, "EAST") //start coordinates
                    .backBefore(7000) //bot must return before 7000 battery level used
                    .withCrew(5) 
                    .collecting(1000, "WOOD")
                    .storingInto("./outputs") //results saves in outputs folder
                    .withName("Island") //simulation name
                    .fire(); //start simulation
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

}
