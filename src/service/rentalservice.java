package service;

import exception.invalidBookingDurationException;
import exception.Vehiclenotavailableexception;
import model.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class rentalservice {
    private Map<String, vehicle> vehicles = new HashMap<>();
    private Map<String, customer> customers = new HashMap<>();
    private List<booking> bookings = new ArrayList<>();

    public void addVehicle(vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
    }

    public void addCustomer(customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Collection<vehicle> getAllVehicles() {
        return vehicles.values();
    }

    public Collection<customer> getAllCustomers() {
        return customers.values();
    }

    public List<booking> getAllBookings() {
        return bookings;
    }

    public vehicle findVehicleById(String vehicleId) {
        return vehicles.get(vehicleId);
    }

    public customer findCustomerById(String customerId) {
        return customers.get(customerId);
    }

    public booking createBooking(String bookingId, String customerId, String vehicleId,
                                 LocalDate startDate, LocalDate endDate)
            throws invalidBookingDurationException, Vehiclenotavailableexception {

        if (startDate == null || endDate == null || !endDate.isAfter(startDate)) {
            throw new invalidBookingDurationException("End date must be after start date.");
        }

        long daysLong = ChronoUnit.DAYS.between(startDate, endDate);
        int days = (int) daysLong;

        if (days <= 0) {
            throw new invalidBookingDurationException("Booking duration must be at least 1 day.");
        }

        customer customer = customers.get(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found.");
        }

        vehicle vehicle = vehicles.get(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        if (!vehicle.isAvailable()) {
            throw new Vehiclenotavailableexception("Vehicle is already booked.");
        }

        double totalCost = vehicle.calculateRentalCost(days);
        booking booking = new booking(bookingId, customer, vehicle, startDate, endDate, days, totalCost);
        bookings.add(booking);
        vehicle.setAvailable(false);
        return booking;
    }

    public void returnVehicle(String vehicleId) {
        vehicle vehicle = vehicles.get(vehicleId);
        if (vehicle != null) {
            vehicle.setAvailable(true);
        }
    }
}
