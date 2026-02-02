package co.com.bancolombia.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shipments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDocument {
    @Id
    private String id;
    private String originCity;
    private String originAddress;
    private String destinationCity;
    private String destinationAddress;
    private String status;
}
