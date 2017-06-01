package ftn.project.eObrazovanje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Transaction;
import ftn.project.eObrazovanje.service.TransactionService;

@RestController
@RequestMapping(value = "api/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.findAll();

		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Transaction>> getTransactionsPage(Pageable page) {
		Page<Transaction> transactions = transactionService.findAll(page);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
		Transaction transaction = transactionService.findOne(id);
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Transaction> savetransaction(@RequestBody Transaction transaction1) {
		Transaction transaction = transaction1;
		transaction = transactionService.save(transaction);

		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Transaction> updatetransaction(@RequestBody Transaction transaction1) {
		// a transaction must exist
		Transaction transaction = transactionService.findOne(transaction1.getId());
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		transaction.setStudent(transaction1.getStudent());
		transaction.setBankAccount(transaction1.getBankAccount());
		transaction.setPrice(transaction1.getPrice());
		transaction.setPurpose(transaction1.getPurpose());
		transaction.setRecipient(transaction1.getRecipient());
		transaction = transactionService.save(transaction);

		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
		Transaction transaction = transactionService.findOne(id);
		if (transaction != null) {
			transactionService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}