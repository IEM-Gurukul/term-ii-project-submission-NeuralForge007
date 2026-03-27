package model;

public class truck extends vehicle {
    public truck(String vehicleId, String brand, String modelName, double baseRentPerDay) {
        super(vehicleId, brand, modelName, baseRentPerDay);
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentPerDay * days * 1.25;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }
}
