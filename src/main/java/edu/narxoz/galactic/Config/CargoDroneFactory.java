package edu.narxoz.galactic.Config;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.HeavyDrone;

public class CargoDroneFactory implements DroneConfigFactory {

    @Override
    public Drone createDrone(String id) {
        return new HeavyDrone(id, 50);
    }
}