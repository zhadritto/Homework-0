package edu.narxoz.galactic.Config;
import edu.narxoz.galactic.drones.Drone;

public interface DroneConfigFactory {
    Drone createDrone(String id);
}