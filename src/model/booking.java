package model;

import java.time.LocalDate;

public class booking {
    private String bookingId;
    private customer customer;
    private vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private int days;
    private double totalCost;

    public booking(String bookingId, customer customer, vehicle vehicle,
                   LocalDate startDate, LocalDate endDate, int days, double totalCost) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.totalCost = totalCost;
    }

    public String getBookingId() {
        return bookingId;
    }

    public customer getCustomer() {
        return customer;
    }

    public vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getDays() {
        return days;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
