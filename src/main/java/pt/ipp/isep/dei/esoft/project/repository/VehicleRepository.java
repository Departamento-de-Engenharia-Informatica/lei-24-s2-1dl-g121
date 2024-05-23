package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {

    private final List<Vehicle> vehicles;

    public VehicleRepository() {
        vehicles = new ArrayList<>();
    }

    public Optional<Vehicle> add(Vehicle vehicle) {
        Optional<Vehicle> newVehicle = Optional.empty();
        boolean operationSuccess = false;

        if (validateVehicle(vehicle)) {
            newVehicle = Optional.of(vehicle);
            operationSuccess = vehicles.add(newVehicle.get());
        }

        if (!operationSuccess) {
            newVehicle = Optional.empty();
        }

        return newVehicle;
    }

    private boolean validateVehicle(Vehicle vehicle) {
        return vehicles.stream().noneMatch(v -> v.getPlate().equalsIgnoreCase(vehicle.getPlate()));
    }

    public List<Vehicle> getVehicles() {
        return List.copyOf(vehicles);
    }

    public Vehicle getVehicleByPlate(String plate) {
        return vehicles.stream()
                .filter(v -> v.getPlate().equalsIgnoreCase(plate))
                .findFirst()
                .orElse(null);
    }

    public boolean updateVehicle(String plate, Vehicle updatedVehicle) {
        Vehicle existingVehicle = getVehicleByPlate(plate);
        if (existingVehicle != null) {
            int index = vehicles.indexOf(existingVehicle);
            vehicles.set(index, updatedVehicle);
            return true;
        }
        return false;
    }

    public boolean deleteVehicle(String plate) {
        Vehicle existingVehicle = getVehicleByPlate(plate);
        if (existingVehicle != null) {
            vehicles.remove(existingVehicle);
            return true;
        }
        return false;
    }
}