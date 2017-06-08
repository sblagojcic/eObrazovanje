package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.model.Transaction;
import ftn.project.eObrazovanje.service.StudentService;
import ftn.project.eObrazovanje.service.TransactionService;
import ftn.project.eObrazovanje.web.dto.TransactionDTO;

@RestController
@RequestMapping(value = "api/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	@Autowired
	StudentService studentService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
		List<Transaction> transactions = transactionService.findAll();
		List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();
		for (Transaction transaction : transactions) {
			transactionsDTO.add(new TransactionDTO(transaction));
		}
		return new ResponseEntity<>(transactionsDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Transaction>> getTransactionsPage(Pageable page) {
		Page<Transaction> transactions = transactionService.findAll(page);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
		Transaction transaction = transactionService.findOne(id);
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new TransactionDTO(transaction), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TransactionDTO> savetransaction(@RequestBody Transaction transaction1) {
		Transaction transaction = transaction1;
		transaction = transactionService.save(transaction);

		return new ResponseEntity<>(new TransactionDTO(transaction), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@RequestMapping(value = "/getFor/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionDTO>> getSubjectForUser(@PathVariable Long id) {
		List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();

		Student student = studentService.findOne(id);
		for (Transaction transaction : student.gettransactions()) {
			transactionsDTO.add(new TransactionDTO(transaction));
		}

		return new ResponseEntity<>(transactionsDTO, HttpStatus.OK);
	}

	// @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	// public ResponseEntity<TransactionDTO> updatetransaction(@RequestBody
	// Transaction transaction1){
	// //a transaction must exist
	// Transaction transaction =
	// transactionService.findOne(transaction1.getId());
	// if (transaction == null) {
	// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	// }
	//
	// transaction.setBankAccount(transaction1.getBankAccount());
	// transaction.setPrice(transaction1.getPrice());
	// transaction.setPurpose(transaction1.getPurpose());
	// transaction.setRecipient(transaction1.getRecipient());
	// transaction = transactionService.save(transaction);
	//
	// return new ResponseEntity<>(new TransactionDTO(transaction),
	// HttpStatus.OK);
	// }

	// @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	// public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
	// Transaction transaction = transactionService.findOne(id);
	// if (transaction != null){
	// transactionService.remove(id);
	// return new ResponseEntity<>(HttpStatus.OK);
	// } else {
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	// }
}