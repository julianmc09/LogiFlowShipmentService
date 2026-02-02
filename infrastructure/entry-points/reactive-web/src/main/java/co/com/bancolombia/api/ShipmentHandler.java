package co.com.bancolombia.api;

import co.com.bancolombia.model.Shipment;
import co.com.bancolombia.model.ShipmentStatus;
import co.com.bancolombia.usecase.shipment.ChangeShipmentStatusUseCase;
import co.com.bancolombia.usecase.shipment.CreateShipmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ShipmentHandler {

        private final CreateShipmentUseCase createShipmentUseCase;
        private final ChangeShipmentStatusUseCase changeShipmentStatusUseCase;

        public Mono<ServerResponse> create(ServerRequest request) {
                return request.bodyToMono(ShipmentRequest.class)
                                .map(req -> Shipment.newShipment(
                                                req.id(),
                                                new co.com.bancolombia.model.Location(req.origin().city(),
                                                                req.origin().address()),
                                                new co.com.bancolombia.model.Location(req.destination().city(),
                                                                req.destination().address())))
                                .flatMap(createShipmentUseCase::create)
                                .flatMap(shipment -> ServerResponse
                                                .created(URI.create("/api/shipments/" + shipment.getId()))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(shipment));
        }

        // DTOs for creation
        public record ShipmentRequest(String id, LocationRequest origin, LocationRequest destination) {
        }

        public record LocationRequest(String city, String address) {
        }

        public Mono<ServerResponse> changeStatus(ServerRequest request) {
                String id = request.pathVariable("id");
                return request.bodyToMono(ChangeStatusRequest.class) // DTO for status change
                                .flatMap(dto -> changeShipmentStatusUseCase.changeStatus(id, dto.newStatus()))
                                .flatMap(shipment -> ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(shipment))
                                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
        }

        // Simple record DTO for status change inside handler to avoid extra file
        public record ChangeStatusRequest(ShipmentStatus newStatus) {
        }
}
