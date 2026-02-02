package co.com.bancolombia.model;

public class Shipment {

    private final String id;
    private final Location origin;
    private final Location destination;
    private ShipmentStatus status;

    // Factory method para crear nuevo shipment (Regla de Negocio)
    public static Shipment newShipment(String id, Location origin, Location destination) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is required");
        }
        return new Shipment(id, origin, destination, ShipmentStatus.IN_TRANSIT);
    }

    // Constructor completo (Persistencia / Mapper / Jackson)
    public Shipment(String id, Location origin, Location destination, ShipmentStatus status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public void deliver() {
        if (status != ShipmentStatus.IN_TRANSIT) {
            throw new IllegalStateException("Only shipments in transit can be delivered");
        }
        this.status = ShipmentStatus.DELIVERED;
    }

    public void markIncident() {
        if (status == ShipmentStatus.DELIVERED) {
            throw new IllegalStateException("Delivered shipment cannot have incidents");
        }
        this.status = ShipmentStatus.INCIDENT;
    }

    public String getId() {
        return id;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public ShipmentStatus getStatus() {
        return status;
    }
}
