package tn.esprit.spring.services;

import tn.esprit.spring.entities.PisteTDO;

import java.util.List;

public interface IPisteServices {

    List<PisteTDO> retrieveAllPistes();

    PisteTDO  addPiste(PisteTDO  piste);

    void removePiste (Long numPiste);

    PisteTDO retrievePiste (Long numPiste);
}
