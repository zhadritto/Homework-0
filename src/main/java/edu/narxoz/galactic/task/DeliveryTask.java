package edu.narxoz.galactic.task;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.cargo.Cargo;

public class DeliveryTask {
    private CelestialBody origin;
    private CelestialBody destination;
    private Cargo cargo;
    private TaskState state;
    private Drone assignedDrone;

    public DeliveryTask(CelestialBody origin, CelestialBody destination, Cargo cargo) {
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        this.state = TaskState.CREATED;
        this.assignedDrone = null;
    }

    public CelestialBody getOrigin() {
        return origin;
    }

    public CelestialBody getDestination() {
        return destination;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public TaskState getState() {
        return state;
    }

    public Drone getAssignedDrone() {
        return assignedDrone;
    }

    // Dispatcher uses these
    public void setState(TaskState state) {
        this.state = state;
    }

    public void setAssignedDrone(Drone drone) {
        this.assignedDrone = drone;
    }

    public double estimateTime() {
        if (assignedDrone == null)
            throw new IllegalStateException("No drone assigned");

        double speed = assignedDrone.speedKmPerMin();
        if (speed <= 0)
            throw new IllegalStateException("Drone speed <= 0");

        return origin.distanceTo(destination) / speed;
    }
}
