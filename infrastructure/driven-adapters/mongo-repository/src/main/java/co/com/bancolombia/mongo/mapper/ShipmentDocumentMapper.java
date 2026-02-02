package co.com.bancolombia.mongo.mapper;

import co.com.bancolombia.model.Location;
import co.com.bancolombia.model.Shipment;
import co.com.bancolombia.model.ShipmentStatus;
import co.com.bancolombia.mongo.document.ShipmentDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ShipmentDocumentMapper {

    public Shipment toEntity(ShipmentDocument document) {
        if (document == null)
            return null;

        return new Shipment(
                document.getId(),
                new Location(document.getOriginCity(), document.getOriginAddress()),
                new Location(document.getDestinationCity(), document.getDestinationAddress()),
                ShipmentStatus.valueOf(document.getStatus()));
    }

    public ShipmentDocument toData(Shipment shipment) {
        if (shipment == null)
            return null;

        return ShipmentDocument.builder()
                .id(shipment.getId())
                .originCity(shipment.getOrigin().getCity())
                .originAddress(shipment.getOrigin().getAddress())
                .destinationCity(shipment.getDestination().getCity())
                .destinationAddress(shipment.getDestination().getAddress())
                .status(shipment.getStatus().name())
                .build();
    }
}
