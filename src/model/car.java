package model;

public class car extends vehicle {
    public car(String vehicleId, String brand, String modelName, double baseRentPerDay) {
        super(vehicleId, brand, modelName, baseRentPerDay);
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentPerDay * days;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}