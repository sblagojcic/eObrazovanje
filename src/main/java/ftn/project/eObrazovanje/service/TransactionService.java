package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.Transaction;
import ftn.project.eObrazovanje.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
    TransactionRepository transactionRepository;
	
    public Transaction findOne(Long id) {
		return transactionRepository.findOne(id);
	}

	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
	
	public Page<Transaction> findAll(Pageable page) {
		return transactionRepository.findAll(page);
	}

	public Transaction save(Transaction Transaction) {
		return transactionRepository.save(Transaction);
	}

	public void remove(Long id) {
		transactionRepository.delete(id);
	}
}