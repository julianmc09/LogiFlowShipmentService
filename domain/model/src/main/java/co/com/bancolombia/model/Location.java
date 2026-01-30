package co.com.bancolombia.model;

public class Location {

    private final String city;
    private final String address;

    public Location(String city, String address) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address is required");
        }
        this.city = city;
        this.address = address;
    }
}
