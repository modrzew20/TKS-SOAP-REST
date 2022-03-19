package com.example.kregielniaspring.api;

import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.managers.ReservationManager;
import com.example.kregielniaspring.model.Lane;
import com.example.kregielniaspring.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import javax.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.ws.rs.Produces;
import java.time.LocalDateTime;
import java.lang.String;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@Validated
public class ReservationController {

    private final ReservationManager reservationManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readAllLane() {
        return ResponseEntity.ok(reservationManager.readAllReservation());
    }


    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readReserv(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid){
        return ResponseEntity.ok(reservationManager.readOneReservation(UUID.fromString(uuid)));

    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String clientsUUID,
                                         @RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String laneUUID,
                                         @RequestParam @NotBlank @Pattern(regexp =
                                                 "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String start,
                                         @RequestParam @NotBlank @Pattern(regexp =
                                                 "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {

        Reservation reservation = reservationManager.addReservation(UUID.fromString(clientsUUID), UUID.fromString(laneUUID),LocalDateTime.parse(start),LocalDateTime.parse(end));
        if(reservation== null)  return ResponseEntity.status(400).build();
        return ResponseEntity.ok(reservation);
    }


    @RequestMapping(value = "/pastClientsReserv/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pastClientReserv(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return ResponseEntity.ok(reservationManager.pastClientReservations(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/presentClientsReserv/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity presentClientReserv(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return ResponseEntity.ok(reservationManager.presentClientReservations(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/pastLaneReserv/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pastLaneReserv(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return ResponseEntity.ok(reservationManager.pastLaneReservations(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/presentLaneReserv/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity presentLaneReserv(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return ResponseEntity.ok(reservationManager.presentLaneReservations(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                                            @RequestParam @NotBlank @Pattern(regexp =
                                                    "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        return ResponseEntity.ok(reservationManager.endReservation(UUID.fromString(uuid),LocalDateTime.parse(end)));
    }

    @RequestMapping(value = "/end/{uuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity endReserv(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid){
        return ResponseEntity.ok(reservationManager.endReservation(UUID.fromString(uuid),LocalDateTime.now()));

    }

    @RequestMapping(value = "/delete/{param}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteReserv(@PathVariable("param") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid ) {
        Reservation reservation = reservationManager.delete(UUID.fromString(uuid));
        if(reservation==null) return ResponseEntity.status(400).build();
        return ResponseEntity.ok(reservation);
    }

}