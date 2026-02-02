package co.com.bancolombia.usecase.shipment;

import co.com.bancolombia.model.Shipment;
import co.com.bancolombia.model.gateways.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateShipmentUseCase {

    private final ShipmentRepository shipmentRepository;

    public Mono<Shipment> create(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
}
