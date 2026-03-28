package gui;

import exception.invalidBookingDurationException;
import exception.Vehiclenotavailableexception;
import model.*;
import service.rentalservice;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    private rentalservice rentalService = new rentalservice();

    private JTextField vehicleIdField = new JTextField();
    private JTextField brandField = new JTextField();
    private JTextField modelNameField = new JTextField();
    private JTextField rentField = new JTextField();
    private JComboBox<String> vehicleTypeBox = new JComboBox<>(new String[]{"car", "bike", "truck"});

    private JTextField customerIdField = new JTextField();
    private JTextField customerNameField = new JTextField();
    private JTextField phoneField = new JTextField();

    private JTextField bookingIdField = new JTextField();
    private JTextField bookingCustomerIdField = new JTextField();
    private JTextField bookingVehicleIdField = new JTextField();
    private JTextField startDateField = new JTextField("2026-03-27");
    private JTextField endDateField = new JTextField("2026-03-29");

    private DefaultTableModel vehicleTableModel;
    private DefaultTableModel customerTableModel;
    private DefaultTableModel bookingTableModel;

    public MainFrame() {
        setTitle("Vehicle Rental Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Vehicles", createVehiclePanel());
        tabs.addTab("Customers", createCustomerPanel());
        tabs.addTab("Bookings", createBookingPanel());
        tabs.addTab("Records", createRecordsPanel());

        add(tabs);
        loadSampleData();
        refreshTables();
    }

    private JPanel createVehiclePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Add Vehicle"));

        form.add(new JLabel("Vehicle ID"));
        form.add(vehicleIdField);

        form.add(new JLabel("Brand"));
        form.add(brandField);

        form.add(new JLabel("Model Name"));
        form.add(modelNameField);

        form.add(new JLabel("Rent Per Day"));
        form.add(rentField);

        form.add(new JLabel("Vehicle Type"));
        form.add(vehicleTypeBox);

        JButton addButton = new JButton("Add Vehicle");
        addButton.addActionListener(e -> addVehicle());

        panel.add(form, BorderLayout.NORTH);
        panel.add(addButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Register Customer"));

        form.add(new JLabel("Customer ID"));
        form.add(customerIdField);

        form.add(new JLabel("Name"));
        form.add(customerNameField);

        form.add(new JLabel("Phone"));
        form.add(phoneField);

        JButton addButton = new JButton("Add Customer");
        addButton.addActionListener(e -> addCustomer());

        panel.add(form, BorderLayout.NORTH);
        panel.add(addButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBookingPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Create Booking"));

        form.add(new JLabel("Booking ID"));
        form.add(bookingIdField);

        form.add(new JLabel("Customer ID"));
        form.add(bookingCustomerIdField);

        form.add(new JLabel("Vehicle ID"));
        form.add(bookingVehicleIdField);

        form.add(new JLabel("Start Date (yyyy-mm-dd)"));
        form.add(startDateField);

        form.add(new JLabel("End Date (yyyy-mm-dd)"));
        form.add(endDateField);

        JButton bookButton = new JButton("Create Booking");
        bookButton.addActionListener(e -> createBooking());

        panel.add(form, BorderLayout.NORTH);
        panel.add(bookButton, BorderLayout.SOUTH);
        return panel;

    }

    private JPanel createRecordsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        vehicleTableModel = new DefaultTableModel(new Object[]{"ID", "Type", "Brand", "Model", "Rent/Day", "Available"}, 0);
        customerTableModel = new DefaultTableModel(new Object[]{"Customer ID", "Name", "Phone"}, 0);
        bookingTableModel = new DefaultTableModel(new Object[]{"Booking ID", "Customer", "Vehicle", "Days", "Total Cost"}, 0);

        JTable vehicleTable = new JTable(vehicleTableModel);
        JTable customerTable = new JTable(customerTableModel);
        JTable bookingTable = new JTable(bookingTableModel);

        panel.add(new JScrollPane(vehicleTable));
        panel.add(new JScrollPane(customerTable));
        panel.add(new JScrollPane(bookingTable));

        return panel;
    }

    private void addVehicle() {
        try {
            String id = vehicleIdField.getText().trim();
            String brand = brandField.getText().trim();
            String model = modelNameField.getText().trim();
            double rent = Double.parseDouble(rentField.getText().trim());
            String type = (String) vehicleTypeBox.getSelectedItem();

            vehicle v;

            if ("bike".equals(type)) {
                v = new bike(id, brand, model, rent);
            } else if ("truck".equals(type)) {
                v = new truck(id, brand, model, rent);
            } else {
                v = new car(id, brand, model, rent);
            }

            rentalService.addVehicle(v);
            refreshTables();
            JOptionPane.showMessageDialog(this, "Vehicle added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void addCustomer() {
        try {
            customer c = new customer(
                    customerIdField.getText(),
                    customerNameField.getText(),
                    phoneField.getText()
            );

            rentalService.addCustomer(c);
            refreshTables();
            JOptionPane.showMessageDialog(this, "Customer added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void createBooking() {
        try {
            rentalService.createBooking(
                    bookingIdField.getText(),
                    bookingCustomerIdField.getText(),
                    bookingVehicleIdField.getText(),
                    LocalDate.parse(startDateField.getText()),
                    LocalDate.parse(endDateField.getText())
            );

            refreshTables();
            JOptionPane.showMessageDialog(this, "Booking created!");
        } catch (Vehiclenotavailableexception | invalidBookingDurationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void refreshTables() {
        vehicleTableModel.setRowCount(0);
        for (vehicle v : rentalService.getAllVehicles()) {
            vehicleTableModel.addRow(new Object[]{
                    v.getVehicleId(),
                    v.getVehicleType(),
                    v.getBrand(),
                    v.getModelName(),
                    v.getBaseRentPerDay(),
                    v.isAvailable()
            });
        }

        customerTableModel.setRowCount(0);
        for (customer c : rentalService.getAllCustomers()) {
            customerTableModel.addRow(new Object[]{
                    c.getCustomerId(),
                    c.getName(),
                    c.getPhone()
            });
        }

        bookingTableModel.setRowCount(0);
        for (booking b : rentalService.getAllBookings()) {
            bookingTableModel.addRow(new Object[]{
                    b.getBookingId(),
                    b.getCustomer().getName(),
                    b.getVehicle().getVehicleId(),
                    b.getDays(),
                    b.getTotalCost()
            });
        }
    }

    private void loadSampleData() {
        rentalService.addVehicle(new car("V1", "Toyota", "Etios", 1500));
        rentalService.addVehicle(new bike("V2", "Yamaha", "FZ", 700));
        rentalService.addVehicle(new bike("V3", "Triumph", "Speed T4", 1200));
        rentalService.addVehicle(new truck("V4", "Tata", "Ace", 2500));
        rentalService.addVehicle(new car("V5", "Hyundai", "Creta", 1700));

        rentalService.addCustomer(new customer("C1", "Rik", "9876543210"));
        rentalService.addCustomer(new customer("C2", "Avijit", "8376553410"));
        rentalService.addCustomer(new customer("C3", "Sayan", "7319353410"));

    }
 }
