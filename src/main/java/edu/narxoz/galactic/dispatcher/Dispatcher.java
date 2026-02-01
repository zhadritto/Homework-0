package edu.narxoz.galactic.dispatcher;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "Drone is not IDLE");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo too heavy for drone");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "Task state is not CREATED");
        }

        task.setAssignedDrone(drone);
        task.setState(TaskState.ASSIGNED);
        drone.setStatus(DroneStatus.IN_FLIGHT);

        return new Result(true, null);
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task is null");
        }
        Drone drone = task.getAssignedDrone();
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "Task state is not ASSIGNED");
        }
        if (drone == null) {
            return new Result(false, "No drone assigned to task");
        }
        if (drone.getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "Drone is not in flight");
        }

        task.setState(TaskState.DONE);
        drone.setStatus(DroneStatus.IDLE);

        return new Result(true, null);
    }

}
