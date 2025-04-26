import java.util.*;
import java.util.concurrent.*;

public class AirportSimulation {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        List<Aircraft> aircrafts = new ArrayList<>();
        Random random = new Random();

        // Spawn 10 aircraft
        for (int i = 0; i < 10; i++) {
            int type = random.nextInt(3);
            int fuel = random.nextInt(100);
            Aircraft a;
            if (type == 0) {
                a = new PassengerPlane("P" + i, fuel, tower);
            } else if (type == 1) {
                a = new CargoPlane("C" + i, fuel, tower);
            } else {
                a = new Helicopter("H" + i, fuel, tower);
            }
            aircrafts.add(a);
            tower.registerAircraft(a);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            Aircraft a = aircrafts.get(random.nextInt(aircrafts.size()));
            if (a.getFuelLevel() < 5) {
                a.send("MAYDAY");
            } else {
                a.requestRunway();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
