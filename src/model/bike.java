package model;

public class bike extends vehicle {
    public bike(String vehicleId, String brand, String modelName, double baseRentPerDay) {
        super(vehicleId, brand, modelName, baseRentPerDay);
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentPerDay * days * 0.8;
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}