/*package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DroneTest {
    private Drone drone;
    private Navigator mockNav;

    @BeforeEach
    public void setupTest() {
        mockNav = mock(Navigator.class);
        drone = new Drone(Compass.NORTH);
        drone.n = mockNav;
    }
    @Test
    public void testFly() {
        String result = drone.fly();
        verify(mockNavigator, times(1)).move(Movement.Forward);
        assertEquals("{\"action\":\"fly\"}", result);
    }
}*/