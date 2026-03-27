package model;

public abstract class vehicle {
    protected String vehicleId;
    protected String brand;
    protected String modelName;
    protected double baseRentPerDay;
    protected boolean available;

    public vehicle(String vehicleId, String brand, String modelName, double baseRentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.modelName = modelName;
        this.baseRentPerDay = baseRentPerDay;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public double getBaseRentPerDay() {
        return baseRentPerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateRentalCost(int days);

    public abstract String getVehicleType();

    @Override
    public String toString() {
        return vehicleId + " - " + getVehicleType() + " - " + brand + " " + modelName;
    }
}