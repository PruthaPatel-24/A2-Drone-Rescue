package ca.mcmaster.se2aa4.island.teamXXX;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DroneTest {

    private Drone drone;
    private RescueDrone resDrone;
    private Navigator mockNav;
    private EchoData mockEchoDataLeft;
    private EchoData mockEchoDataRight;
    private EchoData mockEchoDataForward;

    @BeforeEach
    public void initializeDrone() {
        drone = new Drone();
        resDrone = new RescueDrone();
        mockNav = mock(Navigator.class);
        mockEchoDataLeft = mock(EchoData.class);
        mockEchoDataRight = mock(EchoData.class);
        mockEchoDataForward = mock(EchoData.class);
        resDrone.n = mockNav;
        resDrone.echoDataLeft = mockEchoDataLeft;
        resDrone.echoDataRight = mockEchoDataRight;
        resDrone.echoDataForward = mockEchoDataForward;
    }

    @Test
    public void testFly() {
        String result = drone.fly();
        assertEquals("{\"action\":\"fly\"}", result);
    }

    @Test
    public void testScan() {
        String result = drone.scan();
        assertEquals("{\"action\":\"scan\"}", result);
    }

    @Test
    public void testStop() {
        String result = drone.stop();
        assertEquals("{\"action\":\"stop\"}", result);
    }

    @Test
    public void testTurnLeft() {
        when(mockNav.getC()).thenReturn(Compass.N);
        String result = resDrone.turnLeft();
        verify(mockNav, times(1)).move(Movement.Left);
        assertTrue(result.contains("heading"));
    }

    @Test
    public void testTurnRight() {
        when(mockNav.getC()).thenReturn(Compass.N);
        String result = resDrone.turnRight();
        verify(mockNav, times(1)).move(Movement.Right);
        assertTrue(result.contains("heading"));
    }

    @Test
    public void testEcho() {
        when(mockNav.getC()).thenReturn(Compass.N);
        String result = resDrone.echo(Movement.Forward);
        assertTrue(result.contains("\"action\":\"echo\""));
        assertTrue(result.contains("\"direction\":\"N\""));
    }

}
