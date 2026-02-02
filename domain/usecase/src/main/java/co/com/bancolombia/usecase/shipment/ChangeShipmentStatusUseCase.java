package co.com.bancolombia.usecase.shipment;

import co.com.bancolombia.model.Shipment;
import co.com.bancolombia.model.ShipmentStatus;
import co.com.bancolombia.model.gateways.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ChangeShipmentStatusUseCase {

    private final ShipmentRepository shipmentRepository;

    public Mono<Shipment> changeStatus(String shipmentId, ShipmentStatus newStatus) {
        return shipmentRepository.findById(shipmentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Shipment not found")))
                .map(shipment -> {
                    switch (newStatus) {
                        case DELIVERED -> shipment.deliver();
                        case INCIDENT -> shipment.markIncident();
                        default -> {
                        }
                    }
                    return shipment;
                })
                .flatMap(shipmentRepository::save);
    }
}
