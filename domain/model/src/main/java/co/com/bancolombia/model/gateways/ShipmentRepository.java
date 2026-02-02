package co.com.bancolombia.model.gateways;

import co.com.bancolombia.model.Shipment;
import reactor.core.publisher.Mono;

public interface ShipmentRepository {
    Mono<Shipment> save(Shipment shipment);

    Mono<Shipment> findById(String id);
}
