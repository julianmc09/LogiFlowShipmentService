package co.com.bancolombia.mongo;

import co.com.bancolombia.model.Shipment;
import co.com.bancolombia.model.gateways.ShipmentRepository;
import co.com.bancolombia.mongo.mapper.ShipmentDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ShipmentRepositoryAdapter implements ShipmentRepository {

    private final MongoShipmentRepository repository;
    private final ShipmentDocumentMapper mapper;

    @Override
    public Mono<Shipment> save(Shipment shipment) {
        return repository.save(mapper.toData(shipment))
                .map(mapper::toEntity);
    }

    @Override
    public Mono<Shipment> findById(String id) {
        return repository.findById(id)
                .map(mapper::toEntity);
    }
}
