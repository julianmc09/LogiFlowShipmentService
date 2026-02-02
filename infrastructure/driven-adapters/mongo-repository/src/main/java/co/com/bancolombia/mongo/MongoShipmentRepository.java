package co.com.bancolombia.mongo;

import co.com.bancolombia.mongo.document.ShipmentDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoShipmentRepository extends ReactiveMongoRepository<ShipmentDocument, String> {
}
