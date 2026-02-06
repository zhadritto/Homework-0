package edu.narxoz.galactic.Config;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.LightDrone;

public class ExplorationDroneFactory implements DroneConfigFactory {

    @Override
    public Drone createDrone(String id) {
        return new LightDrone(id, 20);
    }
}