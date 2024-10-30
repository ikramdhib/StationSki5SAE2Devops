package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;
import tn.esprit.spring.tdo.PisteDTO;

import java.util.List;

@Tag(name = "\uD83C\uDFBF Piste Management")
@RestController
@RequestMapping("/piste")
@RequiredArgsConstructor
public class PisteRestController {

    private final IPisteServices pisteServices;

    @Operation(description = "Add Piste")
    @PostMapping(value = "/add",produces = "text/plain")
    public Piste addPiste(@RequestBody PisteDTO piste){
        return  pisteServices.addPiste(piste);
    }
    @Operation(description = "Retrieve all Pistes")
    @GetMapping(value = "/all",produces = "text/plain")
    public List<Piste> getAllPistes(){
        return pisteServices.retrieveAllPistes();
    }

    @Operation(description = "Retrieve Piste by Id")
    @GetMapping(value = "/get/{id-piste}",produces = "text/plain")
    public Piste getById(@PathVariable("id-piste") Long numPiste){
        return pisteServices.retrievePiste(numPiste);
    }

    @Operation(description = "Delete Piste by Id")
    @DeleteMapping(value = "/delete/{id-piste}",produces = "text/plain")
    public void deleteById(@PathVariable("id-piste") Long numPiste){
        pisteServices.removePiste(numPiste);
    }
    

}
