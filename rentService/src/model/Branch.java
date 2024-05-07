package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    private String name;
    private Map<String, Integer> vehicles; // Vehicle type -> Quantity
    private Map<String, Double> prices; // Vehicle type -> Price per hour
    private List<Booking> bookings;

    public Branch(String name) {
        this.name = name;
        vehicles = new HashMap<>();
        prices = new HashMap<>();
        bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getVehicles() {
        return vehicles;
    }

    public void addVehicle(String type, int quantity, double pricePerHour) {
        vehicles.put(type, quantity);
        prices.put(type, pricePerHour);
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}
