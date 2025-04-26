public abstract class Aircraft {
    protected String id;
    protected int fuelLevel;
    protected TowerMediator tower;

    public Aircraft(String id, int fuelLevel, TowerMediator tower) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.tower = tower;
    }

    public void send(String msg) {
        tower.broadcast(msg, this);
    }

    public void requestRunway() {
        boolean granted = tower.requestRunway(this);
        if (granted) {
            System.out.println(id + ": Runway granted, proceeding.");
        } else {
            System.out.println(id + ": Runway busy, waiting...");
        }
    }

    public abstract void receive(String msg);

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }
}
