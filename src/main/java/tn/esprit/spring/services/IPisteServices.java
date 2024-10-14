package tn.esprit.spring.services;

import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.tdo.PisteDTO;

import java.util.List;

public interface IPisteServices {

    List<Piste> retrieveAllPistes();

    Piste  addPiste(PisteDTO piste);

    void removePiste (Long numPiste);

    Piste retrievePiste (Long numPiste);
}
