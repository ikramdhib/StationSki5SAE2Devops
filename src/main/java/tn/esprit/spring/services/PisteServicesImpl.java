package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.PisteDTO;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class PisteServicesImpl implements  IPisteServices{

    private IPisteRepository pisteRepository;

    @Override
    public List<PisteDTO> retrieveAllPistes() {
        return pisteRepository.findAll();
    }

    @Override
    public PisteDTO addPiste(PisteDTO piste) {
        return pisteRepository.save(piste);
    }

    @Override
    public void removePiste(Long numPiste) {
        pisteRepository.deleteById(numPiste);
    }

    @Override
    public PisteDTO retrievePiste(Long numPiste) {
        return pisteRepository.findById(numPiste).orElse(null);
    }
}
