import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new ConcurrentLinkedQueue<>();
    private Queue<Aircraft> takeoffQueue = new ConcurrentLinkedQueue<>();
    private List<Aircraft> allAircraft = new ArrayList<>();
    private boolean runwayBusy = false;

    public void registerAircraft(Aircraft a) {
        allAircraft.add(a);
    }

    @Override
    public void broadcast(String msg, Aircraft sender) {
        System.out.println("TOWER receives from " + sender.getId() + ": " + msg);

        if (msg.equalsIgnoreCase("MAYDAY")) {
            System.out.println("TOWER: EMERGENCY! Clearing runway!");
            landingQueue.clear();
            takeoffQueue.clear();
            notifyAllAircraft("Hold position! Emergency landing in progress.");
            runwayBusy = false;
            landingQueue.add(sender);
        }
    }

    private void notifyAllAircraft(String msg) {
        for (Aircraft a : allAircraft) {
            if (a != null) {
                a.receive(msg);
            }
        }
    }

    @Override
    public boolean requestRunway(Aircraft a) {
        if (runwayBusy) {
            if (a.getFuelLevel() < 10) {
                landingQueue.add(a);
            } else {
                takeoffQueue.add(a);
            }
            return false;
        } else {
            runwayBusy = true;
            System.out.println("TOWER: Runway assigned to " + a.getId());
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("TOWER: " + a.getId() + " finished runway use.");
                    runwayBusy = false;
                }
            }, 3000);
            return true;
        }
    }
}
