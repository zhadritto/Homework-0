package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.Config.*;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.Factory.DroneFactory;
import edu.narxoz.galactic.task.DeliveryTask;

public class Main {

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();
        System.out.println("Factory Method Demo");

        Drone lightDrone = DroneFactory.createDrone("light", "EXP-01", 15
        );

        Drone heavyDrone = DroneFactory.createDrone("heavy", "CAR-01", 40
        );

        Planet mercury = new Planet("Mercury", 1, 2, "None");
        SpaceStation hub = new SpaceStation("Orbital-Hub", 20, 25, 2);
        Cargo minerals = new Cargo(35, "Rare minerals");

        DeliveryTask fmTask = new DeliveryTask(mercury, hub, minerals);
        Result fmResult = dispatcher.assignTask(fmTask, heavyDrone);

        System.out.println("Assign using Factory Method: " + fmResult.ok());
        if (fmResult.ok()) {
            System.out.println("ETA: " + fmTask.estimateTime());
            dispatcher.completeTask(fmTask);

        System.out.println("\n=== Abstract Factory Demo ===");
        DroneConfigFactory explorationFactory = new ExplorationDroneFactory();
        DroneConfigFactory cargoFactory = new CargoDroneFactory();
        Drone explorationDrone = explorationFactory.createDrone("EXP-99");
        Drone cargoDrone = cargoFactory.createDrone("CAR-99");
        Planet europa = new Planet("Europa", 5, 6, "Ice");
        SpaceStation lab = new SpaceStation("Research-Lab", 40, 45, 1);
        Cargo researchKit = new Cargo(10, "Research equipment");

        DeliveryTask afTask1 = new DeliveryTask(europa, lab, researchKit);
        Result afResult1 = dispatcher.assignTask(afTask1, explorationDrone);
        System.out.println("Exploration mission assigned: " + afResult1.ok());
        if (afResult1.ok()) {
            System.out.println("ETA: " + afTask1.estimateTime());
            dispatcher.completeTask(afTask1);
        }

        Planet titan = new Planet("Titan", 15, 18, "Methane");
        SpaceStation depot = new SpaceStation("Cargo-Depot", 80, 85, 3);
        Cargo containers = new Cargo(45, "Industrial containers");

        DeliveryTask afTask2 =new DeliveryTask(titan, depot, containers);

        Result afResult2 = dispatcher.assignTask(afTask2, cargoDrone);

        System.out.println("Cargo mission assigned: " + afResult2.ok());

        if (afResult2.ok()) {
            System.out.println("ETA: " + afTask2.estimateTime());
            dispatcher.completeTask(afTask2);
        }

        System.out.println("\nFinal States ");
        System.out.println("Ex Drone status: " + explorationDrone.getStatus());
        System.out.println("Cargo Drone status: " + cargoDrone.getStatus());
        System.out.println("Last task state: " + afTask2.getState());
    }
}}
