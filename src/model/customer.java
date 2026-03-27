package model;

public class customer {
    private String customerId;
    private String name;
    private String phone;

    public customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return customerId + " - " + name;
    }
}