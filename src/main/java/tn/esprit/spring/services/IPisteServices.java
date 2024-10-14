package tn.esprit.spring.services;

import tn.esprit.spring.entities.PisteDTO;

import java.util.List;

public interface IPisteServices {

    List<PisteDTO> retrieveAllPistes();

    PisteDTO  addPiste(PisteDTO  piste);

    void removePiste (Long numPiste);

    PisteDTO retrievePiste (Long numPiste);
}
