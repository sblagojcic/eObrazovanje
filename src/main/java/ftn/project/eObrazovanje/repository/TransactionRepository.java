package ftn.project.eObrazovanje.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
