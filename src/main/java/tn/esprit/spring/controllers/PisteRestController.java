package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.PisteDTO;
import tn.esprit.spring.services.IPisteServices;

import java.util.List;

@Tag(name = "\uD83C\uDFBF Piste Management")
@RestController
@RequestMapping("/piste")
@RequiredArgsConstructor
public class PisteRestController {

    private static final String ADD_PISTE_URL = "/add";
    private static final String GET_ALL_PISTES_URL = "/all";
    private static final String GET_PISTE_BY_ID_URL = "/get/{id-piste}";
    private static final String DELETE_PISTE_URL = "/delete/{id-piste}";

    private final IPisteServices pisteServices;

    // Méthode privée pour convertir PisteDTO en Piste
    private Piste convertToPiste(PisteDTO pisteDTO) {
        return Piste.builder()
                .namePiste(pisteDTO.getNamePiste())
                .color(pisteDTO.getColor())
                .length(pisteDTO.getLength())
                .slope(pisteDTO.getSlope())
                .build();
    }

    @Operation(description = "Add Piste")
    @PostMapping(ADD_PISTE_URL)
    public ResponseEntity<Piste> addPiste(@RequestBody PisteDTO pisteDTO) {
        Piste piste = convertToPiste(pisteDTO);
        Piste addedPiste = pisteServices.addPiste(piste);
        return new ResponseEntity<>(addedPiste, HttpStatus.CREATED);
    }

    @Operation(description = "Retrieve all Pistes")
    @GetMapping(GET_ALL_PISTES_URL)
    public ResponseEntity<List<Piste>> getAllPistes() {
        List<Piste> pistes = pisteServices.retrieveAllPistes();
        return new ResponseEntity<>(pistes, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Piste by Id")
    @GetMapping(GET_PISTE_BY_ID_URL)
    public ResponseEntity<Piste> getById(@PathVariable("id-piste") Long numPiste) {
        Piste piste = pisteServices.retrievePiste(numPiste);
        if (piste == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(piste, HttpStatus.OK);
    }

    @Operation(description = "Delete Piste by Id")
    @DeleteMapping(DELETE_PISTE_URL)
    public ResponseEntity<Void> deleteById(@PathVariable("id-piste") Long numPiste) {
        pisteServices.removePiste(numPiste);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
