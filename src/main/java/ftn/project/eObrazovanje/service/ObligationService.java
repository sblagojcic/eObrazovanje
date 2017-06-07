package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.Obligation;
import ftn.project.eObrazovanje.repository.ObligationRepository;

@Service
public class ObligationService {

	@Autowired
	ObligationRepository obligationRepository;
	
    public Obligation findOne(Long id) {
		return obligationRepository.findOne(id);
	}

	public List<Obligation> findAll() {
		return obligationRepository.findAll();
	}
	
	public Page<Obligation> findAll(Pageable page) {
		return obligationRepository.findAll(page);
	}

	public Obligation save(Obligation Obligation) {
		return obligationRepository.save(Obligation);
	}

	public void remove(Long id) {
		obligationRepository.delete(id);
	}

}

